package async.crash.com.leethacker;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.HashMap;


//TODO When Screen is Rotated, it does not have all the text that was entered previously. It's most likely calling a new command and setting that as the text

public class MainActivity extends AppCompatActivity {

    private EditText et_user_input;
    private TextWatcher textWatcher = null;
    private CharSequence originalText;
    private String[] selectedCommand;
    private String currentCommand;
    private int randomNum;
    private Boolean screenJustRotated = false;

    private String entireString;

    private static final String TAG ="MY ACTIVITY";

    private String[] directoryNames = new String[]{"sexytimes", "hiddensecret", "Trump Nudes","Roswell report",
            "UFOs","Fountain_of_youth_location", "blackmail", "Illegal Aliens", "Bank Accounts",
    "Social_Security_Numbers", "Billy Joel Classics", "Operation Blackbriar", "NKorea Nukes", "Kardashian_Tape_XXX",
    "Trump Russia Scandal", "Keep out", "Top Secret", "MyLittlePony", "TasteTheRainbow", "LeBron's Hairline", "Stormy Daniels NDA", "Op_Sabotage_Bernie"
    , "Grab em by the p****", "ICE Reports", "Diet coke pictures", "Crooked Hillary", "Crazy Joe Bien", "Wild Bill", "Lyin' Ted Cruz", "Little Rocket Man", "Meuller", "Limewire DL's"};

    private String[] fileNames = new String[]{"sexytimes01", "hiddensecret11", "Nudes"," report",
            "What is that?","Uhh oh", "blackmail", "", "",
            "Resume(1).png.txt.pdf", "Piano Man_iS_AWSOME!", "1020420130210", "the_Launchcodes", "kardashian1", "kardashian2", "kardashian_mouth", "Kim's butt",
            "Trump Russia Scandal", "Keep out", "My Eyes Only!!!", "MyLittlePony", "TasteTheRainbow", "diaperrash_home_remedies", "DANIELS_STORMYYYYY!!!", "Op_Sabotage_Bernie"
            , "quotes by me", "I Have a CRUSH on Ivanka ", "Shoulder Lean (Remix) FEAT:....."};

    private String[] fileTypes = new String[]{".txt", ".bat", ".a", ".asm", ".unx", ".sbl", ".lib", ".hxx", ".jpg", "png", ".wo", ".exe", ".docx", ".xlsx"};


    private String[] commands_ = new String[]{"Ethernet adapter Local Area Connection : \n", "lspci -k \n", "grep -n 'root' /etc/passwd \n", "free\n", "pwd\n", "df /dev/sda1 \n", "head DIARY.txt \n", "sudo dmidecode \n", "ls -al | seashells"};

    private String[] outPut = new String[]{"Connection-specific DNS Suffix . : \n" +
            "IP Address. . . . . . . . . . . . : 192.168.1.35 \n" +
            "Subnet Mask . . . . . . . . . . . : 255.255.255.0 \n" +
            "IP Address. . . . . . . . . . . . : ? \n" +
            "Default Gateway . . . . . . . . . : 192.168.1.10 \n" +
            "\n" +
            "Tunnel adapter Teredo Tunneling Pseudo-Interface: \n" +
            "Connection-specific DNS Suffix . : \n" +
            "IP Address. . . . . . . . . . . . : ? \n" +
            "Default Gateway . . . . . . . . . : \n" ,

            "Connection-specific DNS Suffix . : \n" +
            "IP Address. . . . . . . . . . . . : 192.168.1.35 \n" +
            "Subnet Mask . . . . . . . . . . . : 255.255.255.0 \n" +
            "IP Address. . . . . . . . . . . . : ? \n" +
            "Default Gateway . . . . . . . . . : 192.168.1.10 \n" +
            "\n" +
            "Tunnel adapter Teredo Tunneling Pseudo-Interface: \n" +
            "Connection-specific DNS Suffix . : \n" +
            "IP Address. . . . . . . . . . . . : ? \n" +
            "Default Gateway . . . . . . . . . : \n",

            "00:00.0 Host bridge: Intel Corporation 3rd Gen Core processor DRAM Controller (rev 09)\n " +
                    "Subsystem: Lenovo 3rd Gen Core processor DRAM Controller \n" +
                    "Kernel driver in use: ivb_uncore \n " +
                    "00:02.0 VGA compatible controller: Intel Corporation 3rd Gen Core processor Graphics Controller (rev 09)" + "Subsystem: Lenovo 3rd Gen Core processor Graphics Controller Kernel driver in use: i915 Kernel modules: i915 Subsystem: Lenovo 7 Series/C210 Series Chipset Family USB xHCI Host Controller Kernel driver in use: xhci_hcd 00:16.0 Communication controller: Intel Corporation 7 Series/C216 Chipset Family MEI Controller #1 (rev 04) Subsystem: Lenovo 7 Series/C216 Chipset Family MEI Controller Kernel modules: mei_me \n ",

            "1:root:x:0:0:root:/root:/bin/bash\n" +
            "1042:rootdoor:x:0:0:rootdoor:/home/rootdoor:/bin/csh\n" +
            "3319:initrootapp:x:0:0:initrootapp:/home/initroot:/bin/ksh \n",

            "Usr/DonaldTrump/Desktop/ \n",

            " total           used  free   shared buffers cached\n" +
            "Mem:   1800032       1355288 444744 79440   9068   216236\n" +
            "-/+ buffers/cache: 1129984 670048\n" +
            "Swap:  1832956      995076  837880 \n",

            "Filesystem 1K-blocks Used     Available Use% Mounted on\n" +
                    "/dev/sda1  74985616  48138832 23014620  68%     / \n",
            "3/5/2016\n" +
                    "Ivanka is so hot, I love her more and more each day,\n" +
                    "but that meddeling Bernie Sanders is ruining my chances of finally impressing her. \n" +
                    "If I can manage to win  I can pay back the Russians and actually have some money.\n" +
                    "Oh my gosh, I just had the best, most delightful sip of diet coke.\n" +
                    "This stuff is awesome. \n" +
                    "I could drink 100 of these a day.\n" +
                    "Dang, Lyin' Ted Cruz just tweeted about me today.\n" +
                    "It's 4:03am but I'll respond. I should be sleeping or working on the presidential race\n" +
                    "but I have interns to do all the work for me.\n" +
                    "Kk just tweeted back, I'll go watch some T.V. now. That's all - Donny \"The Best\" Trump \n",

            "dmidecode 2.12\n" +
                    "SMBIOS 2.6 present.\n" +
                    "50 structures occupying 2056 bytes.\n" +
                    "Table at 0x000FCCA0.\n" +
                    "Handle 0x0000, DMI type 0, 24 bytes\n" +
                    "BIOS Information\n" +
                    "Vendor: American Megatrends Inc.\n" +
                    "Version: 080015 \n" +
                    "Release Date: 08/22/2011\n" +
                    "...\n" +
                    "...\n" +
                    "... \n",
            "serving at https://seashells.io/v/Jba3TUv2\n" +
            "total 36\n" +
            "drwxr-xr-x 3 sk sk 4096 Jul 17 15:18 .\n" +
            "drwxr-xr-x 3 root root 4096 Jun 6 2016 ..\n" +
            "-rw------- 1 sk sk 589 Jul 11 18:43 .bash_history\n" +
            "-rw-r--r-- 1 sk sk 220 Jun 6 2016 .bash_logout\n" +
            "-rw-r--r-- 1 sk sk 3771 Jun 6 2016 .bashrc\n" +
            "drwx------ 3 sk sk 4096 Jul 17 15:17 .cache\n" +
            "-rw-r--r-- 1 sk sk 5123 Jul 11 18:24 client.ovpn\n" +
            "-rw-r--r-- 1 sk sk 675 Jun 6 2016 .profile\n" +
            "-rw-r--r-- 1 sk sk 0 Jun 6 2016 .sudo_as_admin_successful \n"};



    private HashMap<String, String> hm_command_output = new HashMap<String, String>();

    private int characterCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        et_user_input = findViewById(R.id.et_user_input);

        randomNum =  (int) (Math.random()* (9));

        selectedCommand = commands_[randomNum].split(" ");

//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        et_user_input.requestFocus();
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        imm.showSoftInput(et_user_input, InputMethodManager.SHOW_IMPLICIT);
        imm.showSoftInput(et_user_input, InputMethodManager.SHOW_FORCED);



        if(savedInstanceState != null){
            originalText = (CharSequence) savedInstanceState.getCharSequence("OG_CHARSEQUENCE");
            screenJustRotated = (Boolean) savedInstanceState.getBoolean("SCREEN_ROTATION");
            et_user_input.setText(originalText);

        }


        // Set selector to end of the edittext
        et_user_input.setSelection(et_user_input.getText().length());


        // Keep cursor scrolled at the end
        et_user_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_user_input.setSelection(et_user_input.length());
            }
        });



        textWatcher = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                et_user_input.setSelection(et_user_input.getText().length());

                originalText = et_user_input.getText();

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                et_user_input.removeTextChangedListener(textWatcher);


                // If characters are entered, then:
                if(count > 0) {

                    // If the amount of characters on this current command is < than the current commands length
                    if (characterCounter < selectedCommand.length) {

                        String randomDirectory = (String) directoryNames[(int) (Math.random() * directoryNames.length)];
                        String randomFile = (String) fileNames[(int) (Math.random() * fileNames.length)];


                        currentCommand = selectedCommand[characterCounter];

                        if(screenJustRotated == false) {

                            originalText = et_user_input.getText().subSequence(0, originalText.length() - count) + currentCommand + " ";

                        }
                        else{
                            originalText = et_user_input.getText().subSequence(0, originalText.length()) + currentCommand + " ";
                        }
                        // Increment the current amount of characters that the user has types
                        characterCounter++;


                        // Call this when screen is rotated
                        et_user_input.setText(originalText);

                        et_user_input.setSelection(et_user_input.getText().length());

                        et_user_input.addTextChangedListener(textWatcher);

                    }

                    else{
                        characterCounter = 0;
                        String selectedOutput;

                        // If the selected command is 'cd', then generate a random directory & File
                        if(currentCommand.equalsIgnoreCase("pwd\n") ){

                            String randomDirectory = (String) directoryNames[(int) (Math.random() * directoryNames.length)];
                            String randomFile = (String) fileNames[(int) (Math.random() * fileNames.length)];

                            selectedOutput = "/Users/donaldjtrump/Desktop: " + randomDirectory +"/" + randomFile + fileTypes[(int) Math.random()* 14];

                        }
                        // else: Space, return, backspace, or some non-character was entered
                        else{
                            selectedOutput = outPut[randomNum];
                            et_user_input.setSelection(et_user_input.length());

                        }


                        randomNum = (int) (Math.random() * (9));

                        selectedCommand =  commands_[ randomNum].split(" ");


                        originalText = et_user_input.getText().subSequence(0, originalText.length() - count) + "\n" + selectedOutput;


                        et_user_input.setText(originalText + "\n");
                        et_user_input.setSelection(et_user_input.getText().length());
                        et_user_input.addTextChangedListener(textWatcher);

                    }
                    // Space, enter, or backspace has been pressed
                }
                else {

                    // If the user deletes the '$' (The very first character), then set the text to have the '$' again
                    if (et_user_input.length() == 0) {
                        et_user_input.setText("$");
                    }

                    et_user_input.setText(originalText);

                    et_user_input.setSelection(et_user_input.getText().length());

                    et_user_input.addTextChangedListener(textWatcher);
                }

                // Set screenJustRotated to false
                screenJustRotated = false;


            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        };

        et_user_input.addTextChangedListener(textWatcher);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Screen has been rotated, set boolean to true so the app will correctly handle the change
        screenJustRotated = true;

        outState.putCharSequence("OG_CHARSEQUENCE", et_user_input.getText());
        outState.putBoolean("SCREEN_ROTATION", screenJustRotated);

    }

    @Override
    protected void onPause() {
        super.onPause();

    }
}
