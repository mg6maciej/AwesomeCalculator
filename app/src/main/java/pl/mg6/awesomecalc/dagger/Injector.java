package pl.mg6.awesomecalc.dagger;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;

public final class Injector {

    private final Context context;
    private final List<Object> testModules = new ArrayList<>();
    private ObjectGraph graph;

    public Injector(Context context) {
        this.context = context;
    }

    public void setTestModules(Object... testModules) {
        this.testModules.clear();
        this.testModules.addAll(Arrays.asList(testModules));
        this.graph = null;
    }

    public void inject(Object root) {
        if (graph == null) {
            graph = createGraph();
        }
        graph.inject(root);
    }

    private ObjectGraph createGraph() {
        List<Object> allModules = new ArrayList<>();
        allModules.add(new AwesomeCalcModule(context));
        allModules.addAll(testModules);
        return ObjectGraph.create(allModules.toArray());
    }
}
