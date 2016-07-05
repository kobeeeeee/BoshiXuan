package www.chendanfeng.com.boishixuan;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.chendanfeng.com.util.LogUtil;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by yangln10784 on 2016/7/6.
 */
public class SettingActivity extends BaseActivity {
    @Bind(R.id.share)
    TextView shareText;
    @Bind(R.id.feedback)
    TextView feedbackText;
    @Bind(R.id.usehelp)
    TextView usehelpText;
    @Bind(R.id.contact)
    TextView contactText;
    @Bind(R.id.phone)
    TextView phoneText;
    @Bind(R.id.aboutus)
    TextView aboutusText;
    @Bind(R.id.clean)
    TextView cleanText;
    @Bind(R.id.logout)
    ImageView logoutButton;
    protected void onCreate(Bundle savedInstanceState) {
        LogUtil.init(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }
}
