package frontend;

import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.light.PointLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;

public class Main extends SimpleApplication {

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    private void lights() {
        AmbientLight ambientLight = new AmbientLight();
        ambientLight.setColor(ColorRGBA.White.mult(0.2f));
        rootNode.addLight(ambientLight);

        PointLight lamp = new PointLight();
        lamp.setPosition(new Vector3f(-30, 30, 0));
        lamp.setRadius(5000);
        lamp.setEnabled(true);
        lamp.setColor(ColorRGBA.Yellow.mult(0.8f));
        rootNode.addLight(lamp);

        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-30, -100, -20));
        sun.setColor(ColorRGBA.White.mult(0.1f));
        rootNode.addLight(sun);
    }

    @Override
    public void simpleInitApp() {
        cam.setLocation(new Vector3f(0, 0, 30));
        cam.lookAt(new Vector3f(0, 0, 0), new Vector3f(0, 1, 0));
        lights();

        Models models = new Models(assetManager);
        models.loadModels();
        Board board = new Board(models);

        rootNode.attachChild(models.chessBoard);
        board.getBoard().forEach(rootNode::attachChild);
    }
}