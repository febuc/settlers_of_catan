package tools;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import resources.ResourcePointer;

/**
 * Used to style nodes.
 * @author Felip
 *
 */
public final class Styling {
	/**
	 * Styles a label.
	 *
	 * @param label
	 */
	public static void styleLabel(Label label, double fontSize) {
		// Set up font
		javafx.scene.text.Font basicFont = Font
				.loadFont(ResourcePointer.class.getResource("PaladinFLF.ttf").toExternalForm(), fontSize);
		DropShadow dropShadow = new DropShadow();
		dropShadow.setRadius(2.0);
		dropShadow.setOffsetX(-2.0);
		dropShadow.setOffsetY(2.0);
		dropShadow.setColor(Color.color(0, 0, 0, 0.4));
		label.setEffect(dropShadow);
		label.setFont(basicFont);
		label.setTextFill(Color.WHITESMOKE);
	}

	/**
	 * Styles a button.
	 *
	 * @param button
	 */
	public static void styleButton(Button button, double fontSize) {
		button.setId("MenuButton2");
		// Set up font
		javafx.scene.text.Font basicFont = Font
				.loadFont(ResourcePointer.class.getResource("PaladinFLF.ttf").toExternalForm(), fontSize);
		DropShadow dropShadow = new DropShadow();
		dropShadow.setRadius(5.0);
		dropShadow.setOffsetX(-5.0);
		dropShadow.setOffsetY(6.0);
		dropShadow.setColor(Color.color(0, 0, 0, 0.5));
		button.setEffect(dropShadow);
		button.setFont(basicFont);
	}
}
