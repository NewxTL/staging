/**
 * @company Sensology.
 */

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * @author Xinx.
 * @date 2018/11/5 17:16
 */
public class VerificationImgTest {

    public static void main(String[] args) {
        createImg();
    }

    private final static String str = "\u6765\u4ed6\u8fd9\u4e0a\u7740\u4e2a\u5730\u5230\u5927\u91cc" +
            "\u8bf4\u5c31\u53bb\u5b50\u5f97\u4e5f\u548c\u90a3\u8981\u4e0b" +
            "\u770b\u5929\u65f6\u8fc7\u51fa\u5c0f\u4e48\u8d77\u4f60\u90fd" +
            "\u628a\u597d\u8fd8\u591a\u6ca1\u4e3a\u53c8\u53ef\u5bb6\u5b66" +
            "\u53ea\u4ee5\u4e3b\u4f1a\u6837\u5e74\u60f3\u751f\u540c\u8001" +
            "\u4e2d\u5341\u4ece\u81ea\u9762\u524d\u5934\u9053\u5b83\u540e" +
            "\u7136\u8d70\u5f88\u50cf\u89c1\u4e24\u7528\u5979\u56fd\u52a8" +
            "\u8fdb\u6210\u56de\u4ec0\u8fb9\u4f5c\u5bf9\u5f00\u800c\u5df1" +
            "\u4e9b\u73b0\u5c71\u6c11\u5019\u7ecf\u53d1\u5de5\u5411\u4e8b" +
            "\u547d\u7ed9\u957f\u6c34\u51e0\u4e49\u4e09\u58f0\u4e8e\u9ad8" +
            "\u624b\u77e5\u7406\u773c\u5fd7\u70b9\u5fc3\u6218\u4e8c\u95ee" +
            "\u4f46\u8eab\u65b9\u5b9e\u5403\u505a\u53eb\u5f53\u4f4f\u542c" +
            "\u9769\u6253\u5462\u771f\u5168\u624d\u56db\u5df2\u6240\u654c" +
            "\u4e4b\u6700\u5149\u4ea7\u60c5\u8def\u5206\u603b\u6761\u767d" +
            "\u8bdd\u4e1c\u5e2d\u6b21\u4eb2\u5982\u88ab\u82b1\u53e3\u653e" +
            "\u513f\u5e38\u6c14\u4e94\u7b2c\u4f7f\u5199\u519b\u5427\u6587" +
            "\u8fd0\u518d\u679c\u600e\u5b9a\u8bb8\u5feb\u660e\u884c\u56e0" +
            "\u522b\u98de\u5916\u6811\u7269\u6d3b\u90e8\u95e8\u65e0\u5f80" +
            "\u8239\u671b\u65b0\u5e26\u961f\u5148\u529b\u5b8c\u5374\u7ad9" +
            "\u4ee3\u5458\u673a\u66f4\u4e5d\u60a8\u6bcf\u98ce\u7ea7\u8ddf" +
            "\u7b11\u554a\u5b69\u4e07\u5c11\u76f4\u610f\u591c\u6bd4\u9636" +
            "\u8fde\u8f66\u91cd\u4fbf\u6597\u9a6c\u54ea\u5316\u592a\u6307" +
            "\u53d8\u793e\u4f3c\u58eb\u8005\u5e72\u77f3\u6ee1\u65e5\u51b3" +
            "\u767e\u539f\u62ff\u7fa4\u7a76\u5404\u516d\u672c\u601d\u89e3" +
            "\u7acb\u6cb3\u6751\u516b\u96be\u65e9\u8bba\u5417\u6839\u5171" +
            "\u8ba9\u76f8\u7814\u4eca\u5176\u4e66\u5750\u63a5\u5e94\u5173" +
            "\u4fe1\u89c9\u6b65\u53cd\u5904\u8bb0\u5c06\u5343\u627e\u4e89" +
            "\u9886\u6216\u5e08\u7ed3\u5757\u8dd1\u8c01\u8349\u8d8a\u5b57" +
            "\u52a0\u811a\u7d27\u7231\u7b49\u4e60\u9635\u6015\u6708\u9752" +
            "\u534a\u706b\u6cd5\u9898\u5efa\u8d76\u4f4d\u5531\u6d77\u4e03" +
            "\u5973\u4efb\u4ef6\u611f\u51c6\u5f20\u56e2\u5c4b\u79bb\u8272" +
            "\u8138\u7247\u79d1\u5012\u775b\u5229\u4e16\u521a\u4e14\u7531" +
            "\u9001\u5207\u661f\u5bfc\u665a\u8868\u591f\u6574\u8ba4\u54cd" +
            "\u96ea\u6d41\u672a\u573a\u8be5\u5e76\u5e95\u6df1\u523b\u5e73" +
            "\u4f1f\u5fd9\u63d0\u786e\u8fd1\u4eae\u8f7b\u8bb2\u519c\u53e4" +
            "\u9ed1\u544a\u754c\u62c9\u540d\u5440\u571f\u6e05\u9633\u7167" +
            "\u529e\u53f2\u6539\u5386\u8f6c\u753b\u9020\u5634\u6b64\u6cbb" +
            "\u5317\u5fc5\u670d\u96e8\u7a7f\u5185\u8bc6\u9a8c\u4f20\u4e1a" +
            "\u83dc\u722c\u7761\u5174\u5f62\u91cf\u54b1\u89c2\u82e6\u4f53" +
            "\u4f17\u901a\u51b2\u5408\u7834\u53cb\u5ea6\u672f\u996d\u516c" +
            "\u65c1\u623f\u6781\u5357\u67aa\u8bfb\u6c99\u5c81\u7ebf\u91ce" +
            "\u575a\u7a7a\u6536\u7b97\u81f3\u653f\u57ce\u52b3\u843d\u94b1" +
            "\u7279\u56f4\u5f1f\u80dc\u6559\u70ed\u5c55\u5305\u6b4c\u7c7b" +
            "\u6e10\u5f3a\u6570\u4e61\u547c\u6027\u97f3\u7b54\u54e5\u9645" +
            "\u65e7\u795e\u5ea7\u7ae0\u5e2e\u5566\u53d7\u7cfb\u4ee4\u8df3" +
            "\u975e\u4f55\u725b\u53d6\u5165\u5cb8\u6562\u6389\u5ffd\u79cd" +
            "\u88c5\u9876\u6025\u6797\u505c\u606f\u53e5\u533a\u8863\u822c" +
            "\u62a5\u53f6\u538b\u6162\u53d4\u80cc\u7ec6";

//    private final static String otherStr =
//            "낙성대는별이떨어진곳이라는뜻이다고려시대때어는날하늘에서가장크고빛나는별하나가땅에" +
//                    "동빙고동서빙고동은조선시대얼음창고가있던동네이다빙고는얼음을저장하는창고를의미한다" +
//                    "강남에있는압구정동은압구정이라는정자가있었기때문에생긴이름이다그정자는조선시대때한" +
//                    "なくともそれはたちのスマイルマスクがどこでもすることだったのだアインシュインするた" +
//                    "ているとがなんだか分からなくなってしまうことがあるかにどっぷりはまっているとやるべ";

    private final static String otherStr =
            "\ub099\uc131\ub300\ub294\ubcc4\uc774\ub5a8\uc5b4\uc9c4\uacf3\uc774\ub77c\ub294\ub73b\uc774\ub2e4\uace0\ub824\uc2dc\ub300\ub54c\uc5b4\ub294\ub0a0\ud558\ub298\uc5d0\uc11c\uac00\uc7a5\ud06c\uace0\ube5b\ub098\ub294\ubcc4\ud558\ub098\uac00\ub545\uc5d0" +
                    "\ub3d9\ube59\uace0\ub3d9\uc11c\ube59\uace0\ub3d9\uc740\uc870\uc120\uc2dc\ub300\uc5bc\uc74c\ucc3d\uace0\uac00\uc788\ub358\ub3d9\ub124\uc774\ub2e4\ube59\uace0\ub294\uc5bc\uc74c\uc744\uc800\uc7a5\ud558\ub294\ucc3d\uace0\ub97c\uc758\ubbf8\ud55c\ub2e4" +
                    "\uac15\ub0a8\uc5d0\uc788\ub294\uc555\uad6c\uc815\ub3d9\uc740\uc555\uad6c\uc815\uc774\ub77c\ub294\uc815\uc790\uac00\uc788\uc5c8\uae30\ub54c\ubb38\uc5d0\uc0dd\uae34\uc774\ub984\uc774\ub2e4\uadf8\uc815\uc790\ub294\uc870\uc120\uc2dc\ub300\ub54c\ud55c" +
                    "\u306a\u304f\u3068\u3082\u305d\u308c\u306f\u305f\u3061\u306e\u30b9\u30de\u30a4\u30eb\u30de\u30b9\u30af\u304c\u3069\u3053\u3067\u3082\u3059\u308b\u3053\u3068\u3060\u3063\u305f\u306e\u3060\u30a2\u30a4\u30f3\u30b7\u30e5\u30a4\u30f3\u3059\u308b\u305f" +
                    "\u3066\u3044\u308b\u3068\u304c\u306a\u3093\u3060\u304b\u5206\u304b\u3089\u306a\u304f\u306a\u3063\u3066\u3057\u307e\u3046\u3053\u3068\u304c\u3042\u308b\u304b\u306b\u3069\u3063\u3077\u308a\u306f\u307e\u3063\u3066\u3044\u308b\u3068\u3084\u308b\u3079";

    private static void createImg(){
        int width=150;
        int height=30;
        int x=0;
        int y=0;
        BufferedImage captchaCanvas=new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);

        Graphics2D captchaBrush=(Graphics2D)captchaCanvas.getGraphics();

        //          画出验证码矩形框。
        captchaBrush.setColor(Color.WHITE);
        captchaBrush.fillRect(x, y, width, height);
        //      3）  创建随机的验证码内容。
        //      4）  画验证码字母数字组合或汉字组合。 //str="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";

        Random random=new Random();

        for(int i=0;i<6;i++){
            char captchaContent;
            if (i == 3){
                int index = random.nextInt(str.length() - 1);
                captchaContent = str.charAt(index);
                captchaBrush.setFont(new Font("华文彩云", Font.PLAIN, 25));
            }else {
                int index = random.nextInt(otherStr.length() - 1);
                captchaContent = otherStr.charAt(index);
                captchaBrush.setFont(new Font("Batang", Font.PLAIN, 25));
            }
            captchaBrush.setColor(randomColor(random));
//            captchaBrush.setFont(new Font(null, Font.PLAIN, 25));

            //并扭曲字母或汉字，旋转范围在正负10度之间。需要采用Graphics2D类，替换之前的Graphics类。
            int angle=random.nextInt(10)-10;
            double theta=angle*Math.PI/180;
            captchaBrush.rotate(theta, x, y);
            captchaBrush.drawString(""+captchaContent, x+8, y+30);
            captchaBrush.rotate(-theta, x + 8, y + 20);
            x+=20;
            //      5）  画干扰线。
            int x1=random.nextInt(width);
            int x2=random.nextInt(width);
            int y1=random.nextInt(height);
            int y2=random.nextInt(height);
            captchaBrush.setColor(Color.BLUE);
            captchaBrush.drawLine(x1, y1, x2, y2);
        }

        // 画干扰符
        Color color;
        for(int i = 0; i < 10; i++){
            color = getRandColor(120, 200);
            captchaBrush.setColor(color);
            captchaBrush.drawOval(random.nextInt(width), random.nextInt(height), 5 + random.nextInt(10), 5 + random.nextInt(10));
            color = null;
        }

        System.out.println(captchaBrush.getFont().getFontName());
        System.out.println(captchaBrush.getFont().getName());

        //      6)  输出到客户端。
        try {
            ImageIO.write(captchaCanvas, "jpg", new File("E:\\IDEA\\staging\\src\\main\\java\\img.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*
	 * 给定范围获得随机颜色
	 */
    private static Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }


    private static Color randomColor(Random random){
        Color[] colors = {
                Color.RED,
                Color.BLACK,
                Color.GREEN,
                Color.MAGENTA,
                Color.GRAY};
        int index = random.nextInt(colors.length - 1);

        return colors[index];
    }
}
