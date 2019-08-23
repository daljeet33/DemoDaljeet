package com.example.dimagibot.ui.main;


import android.os.AsyncTask;

import androidx.recyclerview.widget.RecyclerView;

import com.example.dimagibot.R;
import com.example.dimagibot.config.Constants;
import com.example.dimagibot.data.persistence.DatabaseHelper;
import com.example.dimagibot.data.persistence.UserCommand;

import java.util.List;

import static com.example.dimagibot.config.Constants.HELP;
import static com.example.dimagibot.config.Constants.HISTORY;
import static com.example.dimagibot.config.Constants.PLAY;
import static com.example.dimagibot.config.Constants.RECEIVED;
import static com.example.dimagibot.config.Constants.SAY;
import static com.example.dimagibot.config.Constants.SENT;

public class MainPresenter implements MainMvpPresenter {

    private MainMvpView mainMvpView;
    private String userName;

    MainPresenter(MainMvpView mainMvpView,String userName){
        this.mainMvpView=mainMvpView;
        this.userName=userName;
    }

    @Override
    public void onUserCommandInput(String userInput) {
        if(userInput.isEmpty())
            mainMvpView.showToast(R.string.enter_command,0);
        else{
            String[] spaceSeparatedInputArray =userInput.split(" ");
            String command = spaceSeparatedInputArray[0];
            int inputArrayLength=spaceSeparatedInputArray.length;
            String userArguments = "";
            if(inputArrayLength>1){
                for(int i=1;i<inputArrayLength;i++)
                    userArguments=spaceSeparatedInputArray[i]+" ";
            }
            decipherUserCommand(command,userArguments,userInput);
            mainMvpView.saveUserCommand(userInput);
        }
    }

    @Override
    public void onHistoryFetched(List<UserCommand> userCommands) {
        String historyMessage="";
        if(userCommands.size()<1){
            historyMessage="No User History Found";
        }else{
            for(UserCommand userCommand:userCommands){
                historyMessage+=userCommand.getCommand()+"\n";
            }
        }
        mainMvpView.addNewTextToChatList(historyMessage,RECEIVED);
    }

    private void decipherUserCommand(String command, String userArguments,String userInput) {
        switch (command.toLowerCase()){
            case HELP:
                mainMvpView.addNewTextToChatList(userInput,SENT);
                if(userArguments.isEmpty())
                {
                    mainMvpView.addNewTextToChatList(R.string.help_text,RECEIVED);
                }else{
                switch (userArguments.toLowerCase().trim()){
                    case HELP: mainMvpView.addNewTextToChatList("The Help command can be used as a prefix to a command to get details about that command.",Constants.RECEIVED);
                        break;
                    case SAY: mainMvpView.addNewTextToChatList("The Say command can be used as a prefix to speak aloud the text that follows this command",Constants.RECEIVED);
                        break;
                    case HISTORY: mainMvpView.addNewTextToChatList("The history command displays the history of all commands entered by the user",Constants.RECEIVED);
                        break;
                    default:mainMvpView.showToast(R.string.no_command,0); break;
                }
                }
                break;

            case SAY:
                if(userArguments.isEmpty())
                    mainMvpView.showToast(R.string.provide_argument,0);
                else{
                    mainMvpView.sayTextOutLoud(userArguments);
                    mainMvpView.addNewTextToChatList(userInput, Constants.SENT);
                }

                break;
            case PLAY:
                mainMvpView.addNewTextToChatList(userInput,Constants.SENT);
                break;
            case HISTORY: mainMvpView.fetchUserHistory();
                break;

            default:mainMvpView.showToast(R.string.enter_valid_command,0);
        }
    }


}
