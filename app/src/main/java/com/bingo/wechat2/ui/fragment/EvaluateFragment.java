package com.bingo.wechat2.ui.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Bingo on 16/4/29.
 */
public class EvaluateFragment extends DialogFragment {

    private String[] evaluateStrs = new String[] {"Good", "Just-so-so", "Bad"};
    public static String EVALUATE_RESULT = "evaluate_result";

    public static EvaluateFragment newInstance(String codeName) {

        Bundle args = new Bundle();
        args.putString("codeName", codeName);
        EvaluateFragment fragment = new EvaluateFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("对" + bundle.getString("codeName")+ ",进行评价").setItems(evaluateStrs, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setResult(which);
            }
        });

        return builder.create();
    }

    private void setResult(int which) {
        if(getTargetFragment() == null) {
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EVALUATE_RESULT, evaluateStrs[which]);
        getTargetFragment().onActivityResult(ListItemFragment.LIST_ITEM_REQUEST_CODE, Activity.RESULT_OK , intent);

    }
}
