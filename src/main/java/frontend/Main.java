package frontend;

import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.light.PointLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.shadow.DirectionalLightShadowRenderer;
import com.jme3.shadow.EdgeFilteringMode;
import com.jme3.shadow.PointLightShadowRenderer;

public class Main extends SimpleApplication {

    private Board board;

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

        final int SHADOWMAP_SIZE = 1024;
        PointLightShadowRenderer pointLightShadowRenderer = new PointLightShadowRenderer(assetManager, SHADOWMAP_SIZE);
        pointLightShadowRenderer.setEdgeFilteringMode(EdgeFilteringMode.Bilinear);
        pointLightShadowRenderer.setLight(lamp);
        viewPort.addProcessor(pointLightShadowRenderer);

        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-30, -100, -60));
        sun.setColor(ColorRGBA.White.mult(0.8f));
        rootNode.addLight(sun);

        DirectionalLightShadowRenderer dlsr = new DirectionalLightShadowRenderer(assetManager, SHADOWMAP_SIZE, 3);
        dlsr.setEdgeFilteringMode(EdgeFilteringMode.Bilinear);
        dlsr.setLight(sun);
        viewPort.addProcessor(dlsr);
    }

    @Override
    public void simpleInitApp() {
        setShowSettings(false);
        flyCam.setMoveSpeed(10.0f);

        cam.setLocation(new Vector3f(-50, 40, 0.1f));
        cam.lookAt(new Vector3f(0, 0, 0), new Vector3f(0, 1, 0));
        lights();

        Models models = new Models(assetManager);
        models.loadModels();
        board = new Board(models);

        rootNode.attachChild(models.chessBoard);
        board.getBoard().forEach(rootNode::attachChild);

        rootNode.setShadowMode(RenderQueue.ShadowMode.CastAndReceive);

        initKeys();
    }

    private void initKeys() {
        inputManager.addMapping("Up", new KeyTrigger(KeyInput.KEY_I));
        inputManager.addMapping("Right", new KeyTrigger(KeyInput.KEY_L));
        inputManager.addMapping("Left", new KeyTrigger(KeyInput.KEY_J));
        inputManager.addMapping("Down", new KeyTrigger(KeyInput.KEY_K));
        inputManager.addMapping("Select", new KeyTrigger(KeyInput.KEY_SPACE));
        inputManager.addListener(analogListener, "Left", "Right", "Up", "Down", "Space");
    }

    private final AnalogListener analogListener = new AnalogListener() {
        @Override
        public void onAnalog(String name, float value, float tpf) {
            if (name.equals("Right")) {
                board.selectNext();
            }
        }
    };
}