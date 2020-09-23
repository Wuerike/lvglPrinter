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

public class CardButton extends Container {
    int cardWidth, cardHeight;
    int cardBackground, cardForeground;
    Image iIconImage;
    Label lCardLabel;

    public CardButton(int cardWidth, int cardHeight, int cardBackground, int cardForeground) {
        this.cardWidth = cardWidth;
        this.cardHeight = cardHeight;
        this.cardBackground = cardBackground;
        this.cardForeground = cardForeground;
        setBackForeColors(this.cardBackground, this.cardForeground);
        setBorderStyle(BORDER_ROUNDED);
    }

    public void setIconImage(Image IconImage) {
        this.iIconImage = IconImage;

        try {
            add(new ImageControl(this.iIconImage.smoothScaledFixedAspectRatio((int)(this.cardWidth*0.40), false)),(int) (RIGHT-cardWidth*0.10),(int) (cardWidth*0.10));
        } catch (Exception e) {
            MessageBox.showException(e, true);
        }
    }

    public void setCardLabel(String cardText) {
        this.lCardLabel = new Label(cardText);
        this.lCardLabel.setBackForeColors(this.cardBackground, this.cardForeground);
        this.lCardLabel.setFont(Font.getFont(1));

        for (int i=1; this.lCardLabel.getPreferredWidth() < cardWidth*0.5; i++){
            this.lCardLabel.setFont(Font.getFont(i++));
        }

        add(this.lCardLabel, (int)(LEFT + (cardWidth*0.10)), (int)((BOTTOM) - (cardWidth*0.05)));
    }

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