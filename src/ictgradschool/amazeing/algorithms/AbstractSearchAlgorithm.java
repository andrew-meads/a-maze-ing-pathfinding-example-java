package ictgradschool.amazeing.algorithms;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSearchAlgorithm implements IGraphSearchAlgorithm {

    private List<IAlgorithmListener> listeners = new ArrayList<>();

    protected void fireProgressUpdate() {
        for (IAlgorithmListener l : listeners) {
            l.progressUpdate(this);
        }
    }

    @Override
    public void addAlgorithmListener(IAlgorithmListener l) {
        this.listeners.add(l);
    }

    @Override
    public void removeAlgorithmListener(IAlgorithmListener l) {
        this.listeners.remove(l);
    }
}
