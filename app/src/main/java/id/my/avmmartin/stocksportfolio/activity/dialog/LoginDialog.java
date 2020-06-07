package id.my.avmmartin.stocksportfolio.activity.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import id.my.avmmartin.stocksportfolio.R;

public class LoginDialog extends DialogFragment {
    public interface Listener {
        void clickLogin(LoginDialog dialog);
    }

    private EditText etPassword;
    private Button btnLogin, btnCancel;
    private LoginDialog.Listener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (LoginDialog.Listener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException (
                getActivity().toString() + " must implement LoginDialog.Listener"
            );
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_login, null);

        etPassword = view.findViewById(R.id.etPassword);
        btnLogin = view.findViewById(R.id.btnLogin);
        btnCancel = view.findViewById(R.id.btnCancel);

        builder.setView(view);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCancel();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.clickLogin(LoginDialog.this);
            }
        });

        return builder.create();
    }

    private void clickCancel() {
        dismiss();
    }

    public EditText getEtPassword() {
        return etPassword;
    }

    public Button getBtnLogin() {
        return btnLogin;
    }

    public Button getBtnCancel() {
        return btnCancel;
    }

}
