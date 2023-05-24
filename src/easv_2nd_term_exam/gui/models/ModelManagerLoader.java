package easv_2nd_term_exam.gui.models;

public class ModelManagerLoader {

    private static ModelManagerLoader instance = null;
    private static ModelManager  modelManager = null;

    private ModelManagerLoader()
    {

    }

    public static synchronized ModelManagerLoader getInstance() {
        if (instance == null) {
            instance = new ModelManagerLoader();
        }
        return instance;
    }

    public synchronized ModelManager getModelManager() {
        if (modelManager == null) {
            Thread thread = new Thread(() -> {
                try {
                    modelManager = new ModelManager();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return modelManager;
    }
}
