package backyard.scoretracker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {

    private static final String PLAYER1_SCORE = "player1Score";
    private static final String PLAYER1_TOTAL = "player1Total";
    private static final String PLAYER2_SCORE = "player2Score";
    private static final String PLAYER2_TOTAL = "player2Total";
    private static final String PLAYER3_SCORE = "player3Score";
    private static final String PLAYER3_TOTAL = "player3Total";
    private static final String PLAYER4_SCORE = "player4Score";
    private static final String PLAYER4_TOTAL = "player4Total";

    boolean clicked = false;

    int player1 = 0;
    String player1String = "";
    int player1Total = 0;

    int player2 = 0;
    String player2String = "";
    int player2Total = 0;

    int player3 = 0;
    String player3String = "";
    int player3Total = 0;

    int player4 = 0;
    String player4String = "";
    int player4Total = 0;

    TextView player1Score;
    TextView totalScore1;
    EditText player1Name;

    TextView player2Score;
    TextView totalScore2;
    EditText player2Name;

    TextView player3Score;
    TextView totalScore3;
    EditText player3Name;

    TextView player4Score;
    TextView totalScore4;
    EditText player4Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player1Score = (TextView) findViewById(R.id.player1Score);
        totalScore1 = (TextView) findViewById(R.id.totalScore1);
        player1Name = (EditText) findViewById(R.id.player1);

        player2Score = (TextView) findViewById(R.id.player2Score);
        totalScore2 = (TextView) findViewById(R.id.totalScore2);
        player2Name = (EditText) findViewById(R.id.player2);

        player3Score = (TextView) findViewById(R.id.player3Score);
        totalScore3 = (TextView) findViewById(R.id.totalScore3);
        player3Name = (EditText) findViewById(R.id.player3);

        player4Score = (TextView) findViewById(R.id.player4Score);
        totalScore4 = (TextView) findViewById(R.id.totalScore4);
        player4Name = (EditText) findViewById(R.id.player4);

        Button updateScore = (Button) findViewById(R.id.updateScore);
        Button resetScore = (Button) findViewById(R.id.resetButton);

        resetScore.setEnabled(true);
        resetScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);

                //Setting Dialog Title
                alertDialog.setTitle("Reset Score");

                //Setting Dialog Message
                alertDialog.setMessage("Are you sure you want to reset the score?");
                //On Pressing Setting button
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        player1Score.setText("");
                        totalScore1.setText("");
                        player2Score.setText("");
                        totalScore2.setText("");
                        player3Score.setText("");
                        totalScore3.setText("");
                        player4Score.setText("");
                        totalScore4.setText("");
                        player1String = "";
                        player2String = "";
                        player3String = "";
                        player4String = "";
                        player1Total = 0;
                        player2Total = 0;
                        player3Total = 0;
                        player4Total = 0;

                    }
                });

                //On pressing cancel button
                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.cancel();
                    }
                });

                alertDialog.show();

            }
        });

        updateScore.setEnabled(true);
        updateScore.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (player4Name.getText().toString().equals("") & player3Name.getText().toString().equals("") & player2Name.getText().toString().equals("") & player1Name.getText().toString().equals("")){
                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    //alert.setTitle("Doctor");
                    alert.setMessage("Please enter at least one player's name");
                    alert.setPositiveButton("OK", null);
                    alert.show();
                }
                else {
                    if (!player4Name.getText().toString().equals("")) {
                        final AlertDialog.Builder adb4 = new AlertDialog.Builder(MainActivity.this);
                        final AlertDialog ad4 = adb4.create();
                        final EditText input4 = new EditText(MainActivity.this);
                        ((InputMethodManager) getSystemService(MainActivity.this.INPUT_METHOD_SERVICE))
                                .showSoftInput(input4, InputMethodManager.SHOW_FORCED);
                        input4.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED);

                        adb4.setTitle(player4Name.getText() + "'s Score")
                                .setMessage("Enter " + player4Name.getText() + "'s Score")
                                .setView(input4)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        try {
                                            player4 = Integer.valueOf(input4.getText().toString());
                                            player4String = player4String + input4.getText().toString() + "\n";
                                            player4Total += player4;
                                            player4Score.setText(String.valueOf(player4String));
                                            totalScore4.setText(String.valueOf(player4Total));
                                        } catch (Exception e) {

                                        }
                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        dialog.cancel();
                                        ad4.dismiss();
                                    }
                                }).show();
                    }

                    if (!player3Name.getText().toString().equals("")) {
                        final AlertDialog.Builder adb3 = new AlertDialog.Builder(MainActivity.this);
                        final AlertDialog ad3 = adb3.create();
                        final EditText input3 = new EditText(MainActivity.this);
                        input3.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED);
                        ((InputMethodManager) getSystemService(MainActivity.this.INPUT_METHOD_SERVICE))
                                .showSoftInput(input3, InputMethodManager.SHOW_FORCED);

                        adb3.setTitle(player3Name.getText() + "'s Score")
                                .setMessage("Enter " + player3Name.getText() + "'s Score")
                                .setView(input3)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        try {
                                            player3 = Integer.valueOf(input3.getText().toString());
                                            player3String = player3String + input3.getText().toString() + "\n";
                                            player3Total += player3;
                                            player3Score.setText(String.valueOf(player3String));
                                            totalScore3.setText(String.valueOf(player3Total));
                                        } catch (Exception e) {
                                        }
                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        dialog.cancel();
                                        ad3.dismiss();
                                    }
                                }).show();
                    }

                    if (!player2Name.getText().toString().equals("")) {
                        final AlertDialog.Builder adb2 = new AlertDialog.Builder(MainActivity.this);
                        final AlertDialog ad2 = adb2.create();
                        final EditText input2 = new EditText(MainActivity.this);
                        input2.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED);
                        ((InputMethodManager) getSystemService(MainActivity.this.INPUT_METHOD_SERVICE))
                                .showSoftInput(input2, InputMethodManager.SHOW_FORCED);

                        adb2.setTitle(player2Name.getText() + "'s Score")
                                .setMessage("Enter " + player2Name.getText() + "'s Score")
                                .setView(input2)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        try {
                                            player2 = Integer.valueOf(input2.getText().toString());
                                            player2String = player2String + input2.getText().toString() + "\n";
                                            player2Total += player2;
                                            player2Score.setText(String.valueOf(player2String));
                                            totalScore2.setText(String.valueOf(player2Total));
                                        } catch (Exception e) {
                                        }
                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        dialog.cancel();
                                        ad2.dismiss();
                                    }
                                }).show();
                    }

                    if (!player1Name.getText().toString().equals("")) {
                        final AlertDialog.Builder adb1 = new AlertDialog.Builder(MainActivity.this);
                        final AlertDialog ad1 = adb1.create();
                        final EditText input1 = new EditText(MainActivity.this);
                        input1.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED);
                        ((InputMethodManager) getSystemService(MainActivity.this.INPUT_METHOD_SERVICE))
                                .showSoftInput(input1, InputMethodManager.SHOW_FORCED);

                        adb1.setTitle(player1Name.getText() + "'s Score")
                                .setMessage("Enter " + player1Name.getText() + "'s Score")
                                .setView(input1)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        try {
                                            player1 = Integer.valueOf(input1.getText().toString());
                                            player1String = player1String + input1.getText().toString() + "\n";
                                            player1Total += player1;
                                            player1Score.setText(String.valueOf(player1String));
                                            totalScore1.setText(String.valueOf(player1Total));
                                        } catch (Exception e) {
                                        }
                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        dialog.cancel();
                                        ad1.dismiss();
                                    }
                                }).show();
                    }
                }
            }

        });

        if (savedInstanceState != null) {
            player1 = savedInstanceState.getInt("player1Score");
            player1Total = savedInstanceState.getInt("player1Total");
            player2 = savedInstanceState.getInt("player2Score");
            player2Total = savedInstanceState.getInt("player2Total");
            player3 = savedInstanceState.getInt("player3Score");
            player3Total = savedInstanceState.getInt("player3Total");
            player4 = savedInstanceState.getInt("player4Score");
            player4Total = savedInstanceState.getInt("player4Total");
            player1Score.setText(savedInstanceState.getString("player1"));
            player2Score.setText(savedInstanceState.getString("player2"));
            player3Score.setText(savedInstanceState.getString("player3"));
            player4Score.setText(savedInstanceState.getString("player4"));
        }

    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("player1Score", player1);
        savedInstanceState.putInt("player1Total", player1Total);
        savedInstanceState.putInt("player2Score", player2);
        savedInstanceState.putInt("player2Total", player2Total);
        savedInstanceState.putInt("player3Score", player3);
        savedInstanceState.putInt("player3Total", player3Total);
        savedInstanceState.putInt("player4Score", player4);
        savedInstanceState.putInt("player4Total", player4Total);
        savedInstanceState.putString("player1", player1String);
        savedInstanceState.putString("player2", player2String);
        savedInstanceState.putString("player3", player3String);
        savedInstanceState.putString("player4", player4String);
    }

    @Override
    public void onBackPressed()
    {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        //Setting Dialog Title
        alertDialog.setTitle("Exit");

        //Setting Dialog Message
        alertDialog.setMessage("If you exit, all data will be lost. Are you sure you want to exit?");

        //On Pressing Setting button
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                MainActivity.this.finish();

            }
        });

        //On pressing cancel button
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.cancel();
            }
        });

        alertDialog.show();
    }

    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        player1 = savedInstanceState.getInt("player1Score");
        player1Total = savedInstanceState.getInt("player1Total");
        player2 = savedInstanceState.getInt("player2Score");
        player2Total = savedInstanceState.getInt("player2Total");
        player3 = savedInstanceState.getInt("player3Score");
        player3Total = savedInstanceState.getInt("player3Total");
        player4 = savedInstanceState.getInt("player4Score");
        player4Total = savedInstanceState.getInt("player4Total");
        player1String = savedInstanceState.getString("player1");
        player2String = savedInstanceState.getString("player2");
        player3String = savedInstanceState.getString("player3");
        player4String = savedInstanceState.getString("player4");

    }
}
