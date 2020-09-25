package com.totalcross.ui.components;

import totalcross.sys.Settings;
import totalcross.ui.Container;
import totalcross.ui.ImageControl;
import totalcross.ui.Label;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.Event;
import totalcross.ui.event.EventHandler;
import totalcross.ui.event.PenEvent;
import totalcross.ui.font.Font;
import totalcross.ui.image.Image;

/**
 * Class that defines the main buttons used in the HomeView
 */
public class CardButton extends Container {
    int cardWidth, cardHeight;
    int cardBackground, cardForeground;
    Image iIconImage;
    Label lCardLabel;


    /**
     * Constructor
     * @param cardWidth
     * @param cardHeight
     * @param cardBackground
     * @param cardForeground
     */
    public CardButton(int cardWidth, int cardHeight, int cardBackground, int cardForeground) {
        this.cardWidth = cardWidth;
        this.cardHeight = cardHeight;
        this.cardBackground = cardBackground;
        this.cardForeground = cardForeground;
        setBackForeColors(this.cardBackground, this.cardForeground);
        setBorderStyle(BORDER_ROUNDED);
    }

    /**
     * It receive an Image to use as an icon
     * The image is scaled by setting its width 40% of the received CardButton width size
     * Then it's positioned at 10% of the right border and 10% from the top
     */
    public void setIconImage(Image IconImage) {
        this.iIconImage = IconImage;

        try {
            add(new ImageControl(this.iIconImage.smoothScaledFixedAspectRatio((int)(this.cardWidth*0.40), false)),
                                (int) (RIGHT-cardWidth*0.10),(int) (cardWidth*0.10));
        } catch (Exception e) {
            MessageBox.showException(e, true);
        }
    }

    /**
     * It receive a String to use as an label
     * The background and foreground are setted with the same colors as the CardButton
     * The for loop looks for the bigger font size while keeping its width at 50% of the CardButton width
     * The label is positioned at 10% of the CardButton width from de left and 5% from the bottom
     */
    public void setCardLabel(String cardText) {
        this.lCardLabel = new Label(cardText);
        this.lCardLabel.setBackForeColors(this.cardBackground, this.cardForeground);
        this.lCardLabel.setFont(Font.getFont(1));

        for (int i=1; this.lCardLabel.getPreferredWidth() < cardWidth*0.5; i++){
            this.lCardLabel.setFont(Font.getFont(i++));
        }

        add(this.lCardLabel, (int)(LEFT + (cardWidth*0.10)), (int)((BOTTOM) - (cardWidth*0.05)));
    }

    /**
     * Overrides the onEvent method
     * An event is posted when you click the container and then release (PEN_UP)
     * Others kind of events can be defined at this method
     */

    @Override
    public <H extends EventHandler> void onEvent(Event<H> event) {
        if (event.type == PenEvent.PEN_UP) {
            if ((!Settings.fingerTouch || !hadParentScrolled())
                    && isInsideOrNear(((PenEvent) event).x, ((PenEvent) event).y)) {
                postPressedEvent();
            }
        }
    }
}