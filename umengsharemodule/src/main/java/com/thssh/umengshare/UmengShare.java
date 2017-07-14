package com.thssh.umengshare;

import android.app.Activity;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.ShareBoardlistener;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/30
 */

public class UmengShare {


    public static void share(final Activity activity){
        ShareAction mShareAction = new ShareAction(activity).setDisplayList(
                SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN_FAVORITE,
                SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE)
//                .addButton("umeng_sharebutton_copy", "umeng_sharebutton_copy", "umeng_socialize_copy", "umeng_socialize_copy")
//                .addButton("umeng_sharebutton_copyurl", "umeng_sharebutton_copyurl", "umeng_socialize_copyurl", "umeng_socialize_copyurl")
                .setShareboardclickCallback(new ShareBoardlistener() {
                    @Override
                    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                        if (snsPlatform.mShowWord.equals("umeng_sharebutton_copy")) {
                            Toast.makeText(activity, "复制文本按钮", Toast.LENGTH_LONG).show();
                        } else if (snsPlatform.mShowWord.equals("umeng_sharebutton_copyurl")) {
                            Toast.makeText(activity, "复制链接按钮", Toast.LENGTH_LONG).show();
                        } else {
                            UMWeb web = new UMWeb("http://www.baidu.com");
                            web.setTitle("来自分享面板标题");
                            web.setDescription("来自分享面板内容");
                            web.setThumb(new UMImage(activity, R.drawable.umeng_socialize_share_web));
                            new ShareAction(activity).withMedia(web)
                                    .setPlatform(share_media)
                                    .setCallback(new UMShareListener() {
                                        @Override
                                        public void onStart(SHARE_MEDIA platform) {

                                        }

                                        @Override
                                        public void onResult(SHARE_MEDIA platform) {
                                            if (platform.name().equals("WEIXIN_FAVORITE")) {
                                                Toast.makeText(activity, platform + " 收藏成功啦", Toast.LENGTH_SHORT).show();
                                            } else {
                                                if (platform != SHARE_MEDIA.MORE && platform != SHARE_MEDIA.SMS
                                                        && platform != SHARE_MEDIA.EMAIL
                                                        && platform != SHARE_MEDIA.FLICKR
                                                        && platform != SHARE_MEDIA.FOURSQUARE
                                                        && platform != SHARE_MEDIA.TUMBLR
                                                        && platform != SHARE_MEDIA.POCKET
                                                        && platform != SHARE_MEDIA.PINTEREST

                                                        && platform != SHARE_MEDIA.INSTAGRAM
                                                        && platform != SHARE_MEDIA.GOOGLEPLUS
                                                        && platform != SHARE_MEDIA.YNOTE
                                                        && platform != SHARE_MEDIA.EVERNOTE) {
                                                    Toast.makeText(activity, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
                                                }

                                            }
                                        }

                                        @Override
                                        public void onError(SHARE_MEDIA platform, Throwable t) {
                                            if (platform != SHARE_MEDIA.MORE && platform != SHARE_MEDIA.SMS
                                                    && platform != SHARE_MEDIA.EMAIL
                                                    && platform != SHARE_MEDIA.FLICKR
                                                    && platform != SHARE_MEDIA.FOURSQUARE
                                                    && platform != SHARE_MEDIA.TUMBLR
                                                    && platform != SHARE_MEDIA.POCKET
                                                    && platform != SHARE_MEDIA.PINTEREST

                                                    && platform != SHARE_MEDIA.INSTAGRAM
                                                    && platform != SHARE_MEDIA.GOOGLEPLUS
                                                    && platform != SHARE_MEDIA.YNOTE
                                                    && platform != SHARE_MEDIA.EVERNOTE) {
                                                Toast.makeText(activity, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
                                                if (t != null) {
                                                    Log.d("throw", "throw:" + t.getMessage());
                                                }
                                            }
                                        }

                                        @Override
                                        public void onCancel(SHARE_MEDIA platform) {
                                            Toast.makeText(activity, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .share();
                        }
                    }
                });

        mShareAction.open();
    }
}
