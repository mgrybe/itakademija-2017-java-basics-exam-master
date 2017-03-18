package lt.itakademija.exam.grader;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

import java.math.BigDecimal;
import java.util.*;

public class GradeEvaluatingRunner extends BlockJUnit4ClassRunner {

    private final GradeCounter gradeCounter;

    public GradeEvaluatingRunner(Class<?> klass) throws InitializationError {
        super(klass);
        this.gradeCounter = new GradeCounter();
    }

    @Override
    public void run(RunNotifier notifier) {
        try {
            super.run(notifier);
        } finally {
            gradeCounter.report();
        }
    }

    @Override
    protected Statement methodBlock(final FrameworkMethod method) {
        Statement delegate = super.methodBlock(method);

        ExamTask examTaskAnnotation = method.getAnnotation(ExamTask.class);
        Objects.requireNonNull(examTaskAnnotation, "Missing @ExamTask annotation");

        String taskName = examTaskAnnotation.name();
        if (taskName != null && !taskName.isEmpty()) {
            taskName += ": ";
        } else {
            taskName = "";
        }
        taskName += toSentence(method);

        Task task = gradeCounter.registerTask(taskName, examTaskAnnotation.grade());

        return new StatementWrapper(delegate, task);
    }

    private String toSentence(final FrameworkMethod method) {
        StringBuilder sb = new StringBuilder();

        boolean first = true;
        for (char ch : method.getName().toCharArray()) {
            if (first) {
                sb.append(Character.toUpperCase(ch));
                first = false;
            } else {
                if (Character.isUpperCase(ch)) {
                    sb.append(" ");
                }
                sb.append(Character.toLowerCase(ch));
            }
        }

        return sb.toString();
    }

    private static final class Task {

        private final String name;

        private final double totalGrade;

        private boolean passed;

        public Task(final String name, final double totalGrade) {
            this.name = name;
            this.totalGrade = totalGrade;
        }

        public void setPassed() {
            this.passed = true;
        }

        public boolean hasPassed() {
            return this.passed;
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, totalGrade);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Task other = (Task) obj;
            if (name == null) {
                if (other.name != null)
                    return false;
            } else if (!name.equals(other.name))
                return false;
            if (Double.doubleToLongBits(totalGrade) != Double.doubleToLongBits(other.totalGrade))
                return false;
            return true;
        }

    }

    private static final class GradeCounter {

        private final Set<Task> tasks;

        public GradeCounter() {
            this.tasks = new HashSet<>();
        }

        private void printLine(String text, final Object... arguments) {
            text = String.format(text, arguments);

            int textLength = text.length();
            int totalLineLength = 110;

            System.out.print("*");
            System.out.print(text);

            for (int i = 0; i < (totalLineLength - textLength - 2); i++) {
                System.out.print(" ");
            }
            System.out.println("*");
        }

        public void report() {
            BigDecimal totalGrade = BigDecimal.ZERO;
            BigDecimal scoredGrade = BigDecimal.ZERO;

            List<Task> sortedTasks = new ArrayList<>(tasks);
            Collections.sort(sortedTasks, (t1, t2) -> Double.compare(t1.totalGrade, t2.totalGrade));

            printLine("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ RESULTS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            for (Task task : sortedTasks) {
                BigDecimal taskTotalGradeBigDecimal = BigDecimal.valueOf(task.totalGrade);
                totalGrade = totalGrade.add(taskTotalGradeBigDecimal);
                if (task.hasPassed()) {
                    scoredGrade = scoredGrade.add(taskTotalGradeBigDecimal);
                }

                printLine(" Task: '%s'", task.name);
                printLine("    Grade: '%s", task.totalGrade);
                printLine("    Status: %s", (task.passed ? "PASSED" : "FAILED"));
                printLine("");
            }

            printLine("");
            printLine(" SCORE: %s, TOTAL POINTS: %s", scoredGrade, totalGrade.toPlainString());
            printLine("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }

        public Task registerTask(final String name, final double grade) {
            Task task = new Task(name, grade);
            if (!this.tasks.add(task)) {
                throw new RuntimeException("Task was not registered");
            }
            return task;
        }
    }

    private static final class StatementWrapper extends Statement {

        private final Statement delegate;

        private final Task task;

        public StatementWrapper(final Statement delegate, final Task task) {
            this.delegate = delegate;
            this.task = task;
        }

        @Override
        public void evaluate() throws Throwable {
            delegate.evaluate();

            task.setPassed();
        }

    }
}
