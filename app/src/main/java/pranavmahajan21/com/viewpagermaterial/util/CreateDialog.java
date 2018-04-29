package pranavmahajan21.com.viewpagermaterial.util;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * Use this class only in Activity & never in Application class. Pass context
 * using 'this' from Activity & never Application. Neither can you use
 * getApplicationContext().
 * <p/>
 * Otherwise you will get this exception:
 * android.view.WindowManager$BadTokenException: Unable to add window -- token
 * null is not for an application
 * <p/>
 * Solution:
 * http://stackoverflow.com/questions/2634991/android-1-6-android-view-
 * windowmanagerbadtokenexception-unable-to-add-window
 * <p/>
 * This is a reported bug in android.
 **/
public class CreateDialog {
// Some very important links
//  http://stackoverflow.com/questions/30092737/android-studio-keyboard-shortcut-for-full-screen-mode
//    http://stackoverflow.com/questions/30938620/android-button-drawable-tint
//    http://stackoverflow.com/questions/25482489/what-is-new-in-drawable-tinting-in-android-l-developer-preview-compared-to-previ
//    http://www.curious-creature.com/2009/05/02/drawable-mutations/
//    http://stackoverflow.com/questions/1309629/how-to-change-colors-of-a-drawable-in-android

    Context context;
    ProgressDialog progressDialog;

    public CreateDialog(Context context) {
        this.context = context;
    }

    public ProgressDialog createProgressDialog(String title, String message,
                                               boolean indeterminateState, Drawable drawable) {
//        System.out.println("creating progress dialog");
        progressDialog = new ProgressDialog(context);
        if (title != null) {
            progressDialog.setTitle(title);
        }
        if (message != null) {
            progressDialog.setMessage(message);
        }

        if (drawable != null) {
            progressDialog.setIndeterminateDrawable(drawable);
        }
        progressDialog.setCancelable(false);
        return progressDialog;
    }

    public AlertDialog.Builder createAlertDialogBuilder(String title, String message,
                                                        View customView, boolean isCancelable) {
        // System.out.println("createAlertDialogBuilder");
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        if (title != null) {
            alertDialogBuilder.setTitle(title);
        }
        if (message != null) {
            alertDialogBuilder.setMessage(message);
        }
        if (customView != null) {
            alertDialogBuilder.setView(customView);
        }
        alertDialogBuilder.setCancelable(isCancelable);

        return alertDialogBuilder;
    }

    public void setPositiveButton(AlertDialog.Builder alertDialogBuilder, String buttonText, DialogInterface.OnClickListener onClickListener) {
        if (onClickListener != null) {
            alertDialogBuilder.setPositiveButton(buttonText,
                    onClickListener);
        } else {
            alertDialogBuilder.setPositiveButton(buttonText,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
        }
    }

    public void setNegativeButton(AlertDialog.Builder alertDialogBuilder, String buttonText, DialogInterface.OnClickListener onClickListener) {
        if (onClickListener != null) {
            alertDialogBuilder.setNegativeButton(buttonText,
                    onClickListener);
        } else {
            alertDialogBuilder.setNegativeButton(buttonText,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
        }
    }

    public void showAlertDialog(AlertDialog.Builder alertDialogBuilder) {
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}