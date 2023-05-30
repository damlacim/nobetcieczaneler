package com.damlacim.nobetcieczaneler.ui.customView;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.damlacim.nobetcieczaneler.databinding.CustomAlertDialogBinding;

public class CustomAlertDialog extends Dialog {

    private final CustomAlertDialogBinding dialogBinding;

    public CustomAlertDialog(@NonNull Context context) {
        super(context);
        dialogBinding = CustomAlertDialogBinding.inflate(getLayoutInflater());
        setContentView(dialogBinding.getRoot());
    }

    public void showAnimation(){
        dialogBinding.animationView.setVisibility(View.VISIBLE);
    }

    public void setImage(int imageId) {
        dialogBinding.ivAlertImage.setVisibility(View.VISIBLE);
        dialogBinding.ivAlertImage.setImageResource(imageId);
    }

    public void setTitle(int titleId) {
        dialogBinding.tvTitle.setVisibility(View.VISIBLE);
        dialogBinding.tvTitle.setText(titleId);
    }

    public void setTitle(CharSequence titleStr) {
        dialogBinding.tvTitle.setVisibility(View.VISIBLE);
        dialogBinding.tvTitle.setText(titleStr);
    }

    public void setDescription(int descriptionId) {
        dialogBinding.tvDescription.setVisibility(View.VISIBLE);
        dialogBinding.tvDescription.setText(descriptionId);
    }

    public void setDescription(CharSequence descriptionStr) {
        dialogBinding.tvDescription.setVisibility(View.VISIBLE);
        dialogBinding.tvDescription.setText(descriptionStr);
    }

    public void setPositiveButton(String btnText, View.OnClickListener listener) {
        dialogBinding.btnPositive.setVisibility(View.VISIBLE);
        dialogBinding.btnPositive.setText(btnText);
        dialogBinding.btnPositive.setOnClickListener(listener);
    }

    public void setCancelButton(String btnText, View.OnClickListener listener) {
        dialogBinding.btnCancel.setVisibility(View.VISIBLE);
        dialogBinding.btnCancel.setText(btnText);
        dialogBinding.btnCancel.setOnClickListener(listener);
    }
}
