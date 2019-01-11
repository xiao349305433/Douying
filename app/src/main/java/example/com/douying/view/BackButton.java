package example.com.douying.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;



import example.com.douying.R;


public class BackButton extends Button {

	public BackButton(final Context context, AttributeSet attrs) {
		super(context, attrs);
		setBackgroundResource(R.mipmap.back3);
		this.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				((Activity) context).finish();
			}
		});
	}

	public BackButton(Context context) {
		super(context);
	}
	
}