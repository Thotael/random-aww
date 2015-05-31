package pl.thotael.RandomAww.action;

import pl.thotael.RandomAww.MainAwwActivity;
import pl.thotael.RandomAww.logic.AnimalPageGenerator;

/**
 * Created by SeBa on 2015-05-31.
 */
public class DisplayDogOrCatAction implements NetworkAction {

    private MainAwwActivity activity;

    public DisplayDogOrCatAction(MainAwwActivity activity) {
        this.activity = activity;
    }

    @Override
    public void performAction() {
        activity.displayInWebView(new AnimalPageGenerator().getDogOrCatHtml());
    }
}
