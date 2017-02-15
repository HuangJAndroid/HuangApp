package com.huangj.myapp.fragment;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.huangj.myapp.R;
import com.huangj.myapp.activity.MyPreferenceActivity;
import com.huangj.myapp.activity.AttributeActivity;
import com.huangj.myapp.activity.ScratchActivity;
import com.huangj.myapp.activity.SlidingActivity;
import com.huangj.myapp.activity.VideoActivity;
import com.huangj.myapp.activity.WebViewActivity;
import com.huangj.myapp.activity.ZhuanPanActivity;
import com.huangj.myapp.myinterface.WordWrapListener;
import com.huangj.myapp.view.PrinterTextView;
import com.huangj.myapp.view.WordWrapView;

import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * A simple {@link Fragment} subclass.
 */
public class OneFragment extends Fragment {

    private View view;
    @ViewInject(R.id.one_iv)
    private ImageView one_iv;

    public OneFragment() {
        // Required empty public constructor
    }

    private String[] strs = new String[]{"对话框（打印机）", "PreferenceActivity", "WebView", "大转盘",
            "炫侧滑", "刮刮卡", "视频","状态栏..."};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_one, container, false);
        x.view().inject(this, view);

        //    Glide.with(this).load("http://upload.17u.net/uploadpicbase/2012/02/13/ad/2012021316452454712.jpg").centerCrop().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(one_iv);
        x.image().bind(one_iv, "http://upload.17u.net/uploadpicbase/2012/02/13/ad/2012021316452454712.jpg", new ImageOptions.Builder().build());

        WordWrapView wordWrapView = (WordWrapView) view.findViewById(R.id.view_wordwrap);
        for (int i = 0; i < strs.length; i++) {
            TextView textview = new TextView(getActivity());
            textview.setTextSize(24);
            textview.setText(strs[i]);
            wordWrapView.addView(textview);
        }

        wordWrapView.setWordWrapListener(new WordWrapListener() {
            @Override
            public void onClickListener(View v, int index) {
                switch (index) {
                    case 0:
                        //5.0以上的对话框因为父类的theme背景的.9图四周 边距太宽的原因，和宽度设置无关。其实是填满了的，只是四周是透明的而已
                        //自定义style，继承Theme.Dialog，重写背景属性  R.style.dialog
                        Dialog dialog = new Dialog(getActivity(), R.style.dialog);
                        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_print, null);
                        PrinterTextView printerTextView = (PrinterTextView) view.findViewById(R.id.printertext);
                        printerTextView.setPrintText(" //5.0以上的对话框因为父类的theme背景的.9图四周 边距太宽的原因，和宽度设置无关。其实是填满了的，只是四周是透明的而已\n" +
                                "//自定义style，继承Theme.Dialog，重写背景属性  R.style.dialog");
                        printerTextView.startPrint();
                        dialog.setContentView(view);
                        //返回当前的窗体对象
                        Window win = dialog.getWindow();
                        //设置距离屏幕的边距为0
                        win.getDecorView().setPadding(0, 0, 0, 0);
                        //获取窗体的所有属性
                        WindowManager.LayoutParams lp = win.getAttributes();
                        //设置宽和高
                        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                        lp.height = 500;
                        //将修改的内容给当前窗体进行设置
                        win.setAttributes(lp);
                        //设置窗体弹出和退出的动画(用style描述)
                        //                win.setWindowAnimations(R.style.DialogAnimation);
                        dialog.show();
                        break;

                    case 1:
                        startActivity(new Intent(getActivity(), MyPreferenceActivity.class));
                        break;

                    case 2:
                        startActivity(new Intent(getActivity(), WebViewActivity.class));
                        break;

                    case 3:
                        startActivity(new Intent(getActivity(), ZhuanPanActivity.class));
                        break;

                    case 4:
                        startActivity(new Intent(getActivity(), SlidingActivity.class));
                        break;

                    case 5:
                        startActivity( new Intent(getActivity(), ScratchActivity.class));
                        break;

                    case 6:
                        startActivity(new Intent(getActivity(), VideoActivity.class));
                        break;
                    case 7:
                        startActivity(new Intent(getActivity(), AttributeActivity.class));
                        break;
                }
            }

            @Override
            public void onLongClickListener(View v, int indkex) {
                Toast.makeText(getActivity(), ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}
