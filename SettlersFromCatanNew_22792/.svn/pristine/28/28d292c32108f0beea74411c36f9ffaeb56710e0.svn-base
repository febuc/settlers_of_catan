package view;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import resources.ResourcePointer;

/**
 * Simulates an in game notification.
 * 
 * @author Felip
 *
 */
public class InGameNotification {

	/**
	 * The notification text we want to display.
	 */
	private String notification;
	/**
	 * The minimum height of the notification UI.
	 */
	private double height;
	/**
	 * The length of the notification UI.
	 */
	private double length;

	/**
	 * X-Coordinate of the notification.
	 */
	private double xCoordinate;
	/**
	 * Y-Coordinate of the notification.
	 */
	private double yCoordinate;

	/**
	 * Constructor
	 * 
	 * @param notification
	 *            text we want to display.
	 * @param length
	 *            height of the UI.
	 * @param height
	 *            The minimum height length of the UI.
	 * @param xCoordinate
	 *            The X-Coordinate of the notification.
	 * @param yCoordinate
	 *            The Y-Coordinate of the notification.
	 */
	public InGameNotification(String notification, double length, double height, double xCoordinate,
			double yCoordinate) {
		this.notification = notification;
		this.height = height;
		this.length = length;
		this.xCoordinate = xCoordinate - length / 2;
		this.yCoordinate = yCoordinate;
	}

	/**
	 * Displays the notification on the screen/scene.
	 * 
	 * @return The notification as a button.
	 */
	public Button getNotificationButton() {
		Button notificationButton = getButtonShape();
		notificationButton.setMouseTransparent(true);
		notificationButton.setId("notification");
		Font fontNotification = Font.loadFont(ResourcePointer.class.getResource("PaladinFLF.ttf").toExternalForm(),
				height / 4);
		notificationButton.setFont(fontNotification);
		notificationButton.setText(notification);
		return notificationButton;
	}

	/**
	 * 
	 * @return An "Accept" button
	 */
	public Button getAcceptButton() {
		Button notificationButton = getButtonShape();
		notificationButton.setId("avatar");
		return notificationButton;
	}

	/**
	 * Creates a default button
	 * 
	 * @return
	 */
	private Button getButtonShape() {
		Button notificationButton = new Button();
		notificationButton.setMinHeight(height);
		notificationButton.setMaxHeight(height);
		notificationButton.setMaxWidth(length);
		notificationButton.setMinWidth(length);
		notificationButton.relocate(xCoordinate, yCoordinate);
		DropShadow dropShadow = new DropShadow();
		dropShadow.setRadius(5.0);
		dropShadow.setOffsetX(-5.0);
		dropShadow.setOffsetY(6.0);
		dropShadow.setColor(Color.color(0, 0, 0, 0.8));
		notificationButton.setEffect(dropShadow);
		return notificationButton;
	}
}
