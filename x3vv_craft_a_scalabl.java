import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class DataPipeline {
    private String name;
    private List<DataProcessor> processors;

    public DataPipeline(String name) {
        this.name = name;
        this.processors = new ArrayList<>();
    }

    public void addProcessor(DataProcessor processor) {
        processors.add(processor);
    }

    public void process(Data data) {
        for (DataProcessor processor : processors) {
            processor.process(data);
        }
    }
}

interface DataProcessor {
    void process(Data data);
}

class Data {
    private String value;

    public Data(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

class ConsoleLogger implements DataProcessor {
    @Override
    public void process(Data data) {
        System.out.println("Processed data: " + data.getValue());
    }
}

class DataValidator implements DataProcessor {
    @Override
    public void process(Data data) {
        if (data.getValue().length() < 5) {
            System.out.println("Invalid data: " + data.getValue());
        }
    }
}

public class x3vv_craft_a_scalabl {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        DataPipeline pipeline = new DataPipeline("MyPipeline");
        pipeline.addProcessor(new ConsoleLogger());
        pipeline.addProcessor(new DataValidator());

        for (int i = 0; i < 10; i++) {
            Data data = new Data("Data-" + i);
            executor.submit(() -> pipeline.process(data));
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
    }
}