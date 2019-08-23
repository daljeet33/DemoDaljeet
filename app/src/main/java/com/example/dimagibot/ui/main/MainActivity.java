package com.example.dimagibot.ui.main;

import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.EditText;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dimagibot.R;
import com.example.dimagibot.config.Constants;
import com.example.dimagibot.data.model.ChatMessageObject;
import com.example.dimagibot.data.persistence.DatabaseHelper;
import com.example.dimagibot.data.persistence.UserCommand;
import com.example.dimagibot.ui.base.BaseActivity;
import com.example.dimagibot.ui.main.adapter.ChatViewAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainMvpView, TextToSpeech.OnInitListener {
    @BindView(R.id.input_edit_text)
    EditText inputEditText;
    @BindView(R.id.btn_send)
    Button btnSend;
    @BindView(R.id.chat_recycler_view)
    RecyclerView chatRecyclerView;
    private MainMvpPresenter mainMvpPresenter;
    private TextToSpeech userTextToSpeech;
    private List<ChatMessageObject> chatList = new ArrayList<>();
    private ChatViewAdapter chatViewAdapter;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        userName = getIntent().getStringExtra(Constants.USERNAME);
        mainMvpPresenter = new MainPresenter(this,userName);
        initChatRecyclerView();
        initTextToSpeech();
    }

    private void initChatRecyclerView() {
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatViewAdapter=new ChatViewAdapter(chatList);
        chatRecyclerView.setAdapter(chatViewAdapter);
    }

    private void initTextToSpeech() {
        userTextToSpeech = new TextToSpeech(this, this);
    }

    //Text To Speech Init Listener
    @Override
    public void onInit(int status) {
        if (status != TextToSpeech.ERROR)
            userTextToSpeech.setLanguage(Locale.US);
    }

    @OnClick(R.id.btn_send)
    public void onViewClicked() {
        mainMvpPresenter.onUserCommandInput(inputEditText.getText().toString());
        inputEditText.setText("");
    }


    @Override
    public void sayTextOutLoud(String userInpusst) {
        userTextToSpeech.speak(userInpusst, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void addNewTextToChatList(String userInput,String type) {
        ChatMessageObject userMessageObject= new ChatMessageObject();
        userMessageObject.setChatMessage(userInput);
        userMessageObject.setType(type);
        chatList.add(userMessageObject);
        chatViewAdapter.updateList(chatList);
    }

    @Override
    public void addNewTextToChatList(int chatInput, String type) {
        addNewTextToChatList(getString(chatInput),type);
    }

    @Override
    public void fetchUserHistory() {
        new FetchHistoryTask().execute();
    }

    @Override
    public void saveUserCommand(String userInput) {
        new SaveCommandTask().execute(userInput);
    }

    class FetchHistoryTask extends AsyncTask<Void,Void, List<UserCommand>> {

        @Override
        protected List<UserCommand> doInBackground(Void... strings) {
            List<UserCommand> commandHistory= DatabaseHelper.getInstance(MainActivity.this).getAppDatabase().userDao().getUserCommands(userName);
            return commandHistory;
        }

        @Override
        protected void onPostExecute(List<UserCommand> userCommands) {
            super.onPostExecute(userCommands);
            mainMvpPresenter.onHistoryFetched(userCommands);
        }
    }


    class SaveCommandTask extends AsyncTask<String,Void, Void> {

        @Override
        protected Void doInBackground(String... strings) {
            UserCommand userCommand =new UserCommand();
            userCommand.setCommand(strings[0]);
            userCommand.setUsername(userName);
            userCommand.setCreated_time(String.valueOf(System.currentTimeMillis()));
            DatabaseHelper.getInstance(MainActivity.this).getAppDatabase().userDao().insert(userCommand);

            return null;
        }

        @Override
        protected void onPostExecute(Void avoid) {
            super.onPostExecute(avoid);
        }
    }


}
