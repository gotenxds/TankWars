package machanet.TankWars;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

public class MainScreen extends Activity
{
    private final EditText editText = new EditText(this);

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainscreen);
    }

    public void versusModeClicked(View view)
    {
        Intent intent = new Intent(getApplicationContext(), ControlScreen.class);

        initializeDataForControlScreen(intent);

        startActivity(intent);
    }

    private void initializeDataForControlScreen(Intent intent)
    {
        askForIp();

        intent.putExtra("ip", editText.getText());
        intent.putExtra("port", 6000);
    }

    private void askForIp()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
            .setTitle("Server IP required")
            .setMessage("Please insert the server IP")
            .setView(createTextEdit())
            .show();
    }

    private View createTextEdit()
    {
        editText.setText("192.168.1.100");
        editText.setInputType(InputType.TYPE_CLASS_TEXT);

        return editText;
    }
}
