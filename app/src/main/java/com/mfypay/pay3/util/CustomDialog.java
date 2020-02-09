package com.mfypay.pay3.util;//package com.mfypay.pay3.util;
//
//import android.app.ActionBar;
//import android.app.Dialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.graphics.Bitmap;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//
//
//
//public class CustomDialog  extends Dialog {
//    public CustomDialog(Context context) {
//        super(context);
//    }
//
//
//    public CustomDialog(Context context, int theme) {
//        super(context, theme);
//    }
//
//
//    public static class Builder {
//        private Context context;
//
//        private OnClickListener positiveButtonClickListener;
//
//
//        public Builder(Context context) {
//            this.context = context;
//        }
//
//
//        /**
//         * Set the positive button resource and it's listener
//         *  
//         *
//         * @param positiveButtonText
//         * @return
//         */
//        public Builder setPositiveButton(int positiveButtonText,
//                                         OnClickListener listener) {
//
//            this.positiveButtonClickListener = listener;
//            return this;
//        }
//
//        public Builder setPositiveButton(String positiveButtonText,
//                                         OnClickListener listener) {
//            this.positiveButtonClickListener = listener;
//            return this;
//        }
//
//        public CustomDialog create(Bitmap bitmap) {
//            LayoutInflater inflater = (LayoutInflater) context
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//            final CustomDialog dialog = new CustomDialog(context,
//                    R.style.Dialog);
//            View layout = inflater.inflate(R.layout.dialog_normal_layout, null);
//
//            ImageView img = layout.findViewById(R.id.xiu_fu_page_flage);
//            img.setImageBitmap(bitmap);
//
//
//            if (positiveButtonClickListener != null) {
//                ((ImageButton) layout.findViewById(R.id.dialog_close_btn))
//                        .setOnClickListener(new View.OnClickListener() {
//                            public void onClick(View v) {
//                                positiveButtonClickListener.onClick(dialog,
//                                        DialogInterface.BUTTON_POSITIVE);
//                            }
//                        });
//            }
//
//            dialog.addContentView(layout, new ActionBar.LayoutParams(
//                    ActionBar.LayoutParams.FILL_PARENT, ActionBar.LayoutParams.WRAP_CONTENT));
//
//
//            dialog.setContentView(layout);
//            return dialog;
//        }
//
//    }
//
//}
