import java.util.Comparator;

public class SpriteComparitor implements Comparator<Sprite>{

    @Override
	public int compare(Sprite x, Sprite y) {
		return Double.compare(x.getLayer(), y.getLayer());
	}
}
