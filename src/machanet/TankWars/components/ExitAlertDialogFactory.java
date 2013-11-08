package machanet.TankWars.components;

import android.app.AlertDialog;
import android.content.Context;

public class ExitAlertDialogFactory
{
    public static void alertUser(Context context)
    {
        new AlertDialog.Builder(context)
                .setTitle("Error")
                .setMessage("Could not connect to blueTooth.")
                .setCancelable(true)
                .show();
    }
}
