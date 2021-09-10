package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
 
// 表示クラス
public class Display implements ActionListener
{
    // 画面
    public static JFrame disp;
 
    // パネル
    private JPanel top_panel, mid_panel, bottom_Panel;
 
    // メッセージ
    private JLabel msg_lbl;
 
    // 親のカード
    private JLabel parent_lbl, parent_suit_lbl, parent_no_lbl;
 
    // 子のカード
    private JLabel child_lbl, child_suit_lbl, child_no_lbl;
 
    // ボタン
    private JButton btn_high, btn_low,btn_Re;
 
    // プレイヤー
    private Player parent, child;
 
    
    public Display( Player prn, Player chl )
    {
       
        parent = prn;
        child  = chl;
 
        // ゲーム画面全体の表示設定
        disp = new JFrame("HIGH & LOW");  // 画面を生成
        disp.setSize(720, 480);           // 表示サイズを設定
        disp.setLocationRelativeTo(null); // 画面の表示位置を中央
        disp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //×ボタンで画面を閉じる
        disp.setResizable(false);         // 画面サイズを変更
 
        // トップパネル
        top_panel = new JPanel(); // パネルを生成
        setPanel(top_panel, Color.WHITE, null, new Dimension(480, 50) ); // パネルの背景色、レイアウト、サイズ
        disp.add( top_panel, BorderLayout.NORTH ); // 画面上部にパネル
 
        // メッセージラベル
        msg_lbl = new JLabel("HIGHかLOWか当ててください。"); // ラベルを生成
        top_panel.add(msg_lbl); // トップパネルに追加
        setLabelFont(msg_lbl, Color.BLACK, 0, 20, 720, 30, 30, false); // ラベルフォント
 
       
        mid_panel = new JPanel(); 
        setPanel(mid_panel, Color.ORANGE, null, new Dimension(480, 180) ); // パネルの背景色、レイアウト、サイズ
        disp.add( mid_panel, BorderLayout.CENTER ); 
        
        // 親カード情報
        parent_lbl = new JLabel("私のカード");
        parent_suit_lbl = new JLabel( getSuitIcon( parent.GetSuit() ) ); // マーク表示
        parent_no_lbl = new JLabel( getNoStr( parent.GetNo() ) );  // 数字表示
        
     
        mid_panel.add(parent_lbl);
        mid_panel.add(parent_suit_lbl);
        mid_panel.add(parent_no_lbl);
        
        // ラベルフォント
        setLabelFont(parent_lbl, Color.WHITE, 180, 80, 100, 20, 14, false );      
        setLabelFont(parent_suit_lbl, Color.WHITE, 150, 40, 160, 200, 16, false );
        setLabelFont(parent_no_lbl, Color.WHITE, 150, 105, 160, 200, 16, true );
 
        // 子カード情報
        child_lbl = new JLabel("あなたのカード");
        child_suit_lbl = new JLabel("");
        child_no_lbl = new JLabel("？");
        
        // ミドルパネル
        mid_panel.add(child_lbl);
        mid_panel.add(child_suit_lbl);
        mid_panel.add(child_no_lbl);
        
        // ラベルフォント
        setLabelFont(child_lbl, Color.WHITE, 410, 80, 150, 20, 14, false );
        setLabelFont(child_suit_lbl, Color.LIGHT_GRAY, 400, 40, 160, 200, 16, false );
        setLabelFont(child_no_lbl, Color.LIGHT_GRAY, 400, 105, 160, 200, 16, true );
 
        // ボトムパネル
        bottom_Panel = new JPanel();
        setPanel( bottom_Panel, Color.LIGHT_GRAY, new BorderLayout(), new Dimension(480, 50) ); // パネルの背景色、レイアウト、サイズを設定
        disp.add( bottom_Panel, BorderLayout.SOUTH ); // 画面下部パネル
 
        // HIGHボタン
        btn_high = new JButton("HIGH");
        setButton( btn_high, this, 240, 50, 20 );        // ボタンフォント、イベント
        bottom_Panel.add( btn_high, BorderLayout.WEST ); // ボトムパネル左側にボタン
 
        // LOWボタン
        btn_low = new JButton("LOW");
        setButton( btn_low, this, 240, 50, 20 );        // ボタンフォント、イベント
        bottom_Panel.add( btn_low, BorderLayout.EAST ); // ボトムパネル右側にボタン
        
        // Retryボタン
        btn_Re = new JButton("Retry");
        setButton( btn_Re, this, 240, 50, 20 );        // ボタンフォント、イベント
        bottom_Panel.add( btn_Re, BorderLayout.CENTER );
 
        // ゲーム画面を表示
        disp.setVisible(true);
    }
    // HIGHかLOWイベント
    public void actionPerformed(ActionEvent e)
    {
        String cmd     = e.getActionCommand(); 
        int parent_no  = parent.GetNo();   // 親カード
        int child_no   = child.GetNo();    // 子カード
        int child_suit = child.GetSuit();  // 子カード
 
        // 子のカードオープン
        child_no_lbl.setBackground(Color.WHITE);            // 数字の背景色
        child_no_lbl.setText( getNoStr( child.GetNo() ) );  // 数字の表示データ
        child_suit_lbl.setBackground(Color.WHITE);          // マークの背景色
        child_suit_lbl.setIcon( getSuitIcon( child_suit ) );// マークの表示データ
 
        // 押されたボタンに応じた処理を行う
        if( cmd.equals("HIGH") ) // HIGHボタンが押された時の処理
        {    	
            
            btn_high.setBackground(Color.GREEN);
 
           
            if( parent_no < child_no ) // 子の方が大きい
                msg_lbl.setText("あなたの勝ちです！");
            else if( child_no < parent_no ) 
                msg_lbl.setText("あなたの負けです！");
            else 
                msg_lbl.setText("引き分けです！");
        }
        else if( cmd.equals("LOW") ) // LOWボタンが押された時の処理
        {
            btn_low.setBackground(Color.GREEN); // ボタンの色変更
 
           
            if( parent_no < child_no ) 
                msg_lbl.setText("あなたの負けです");
            else if( child_no < parent_no ) 
                msg_lbl.setText("あなたの勝ちです");
            else 
                msg_lbl.setText("引き分けです");
        }
        else if( cmd.equals("Retry") ) // Retryボタンが押された時の処理
        {
            btn_low.setBackground(Color.GREEN); // ボタンの色変更
         // ゲーム画面を表示
            disp.setVisible(false);
            GameMain.run();
 
           
        }
 
            return;
    }
	
    // パネルフォント設定
    public static void setPanel(JPanel panel, Color color, BorderLayout layout, Dimension dimension )
    {
        panel.setBackground(color);        // 背景色を設定
        panel.setLayout(layout);           // レイアウトを設定
        panel.setPreferredSize(dimension); // 表示サイズを設定
 
        return;
    }
 
    // ラベルフォント設定
    public static void setLabelFont(JLabel label, Color clr, 
                                    int x_pos,   int y_pos,
                                    int x_size,  int y_size,
                                    int strSize, boolean opq )
    {
        label.setBackground(clr);        // 背景色を設定
        label.setLocation(x_pos, y_pos); // 表示位置を設定
        label.setSize(x_size, y_size);   // 表示サイズを設定
        label.setFont( new Font("ＭＳ ゴシック", Font.PLAIN, strSize=20) ); 
        label.setHorizontalAlignment(JLabel.CENTER); 
        label.setVerticalAlignment(JLabel.CENTER);  
        label.setOpaque(opq); 
 
        return;
    }
	
    // ボタン設定
    public static void setButton(JButton btn, ActionListener al, int x_size, int y_size, int strSize )
    {
        btn.setPreferredSize(new Dimension(x_size, y_size));      // 表示サイズを設定
        btn.setFont( new Font("ＭＳ ゴシック", Font.PLAIN, strSize) ); 
        btn.addActionListener(al); // ボタンが押された時のイベントを受け取れるように設定
 
        return;
    }
 
    // マーク
    public static ImageIcon getSuitIcon( int suit )
    {
        ImageIcon icon;
 
        //画像を読み込み
        switch(suit)
        {
            case 0: // スペード
                icon = new ImageIcon("./src/image/spade.jpeg");
                return icon;
	
            case 1: // ハート
                icon = new ImageIcon("./src/image/heart.jpeg");
                return icon;
	
            case 2: // ダイヤ
                icon = new ImageIcon(".src/image/diamond.jpeg");
                return icon;
	
            case 3: // クラブ
                icon = new ImageIcon("./src/image/clover.jpeg");
                return icon;
 
            default: 
                return null;
        }
    }
	
    // 数字に応じた表示文字列を取得
    public static String getNoStr( int no )
    {
        switch(no)
        {
            case 1: // エース
                return "A";
 
            case 11: // ジャック
                return "J";
	
            case 12: // クイーン
                return "Q";
	
            case 13: // キング
                return "K";
 
            default: // 上記以外は数字をそのまま文字列として出力する
                return String.valueOf(no);
        }
    }
}
