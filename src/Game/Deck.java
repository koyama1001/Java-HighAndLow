package Game;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


//カードクラス
class Card
{
	int mark; 
	int no;//数字
}

//デッキクラス
public class Deck {

	//定義
	final int Total_Card = 52;//カード総枚数
	
	private List Card_list;//リスト
	
	 // カード番号
	private int Card_index = 0;
	

	public Deck()
	{
		//カード初期化
		Card_index=0; 
		
		// 山札初期化
		Card_list = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51));
	
        // 山札シャッフル
        Collections.shuffle(Card_list);
	}
	
	public Card GetCard()
	    {
	        Card card_info = new Card(); // カード情報格納
	        
	        int card_no = 0; // カード番号
	 
	        //番号取得
	        card_no = (int) Card_list.get(Card_index); 
		
	        // カード番号+1
	        Card_index++;
		
	        // 山札を初期化
	        if( Total_Card <= Card_index )
	        {
	        	Card_index = 0; // 先頭に戻す
	            Collections.shuffle(Card_list); // 山札をシャッフル
	        }
		
	        card_info.mark = card_no / 13;      
	        card_info.no   = (card_no % 13) + 1; 
	 
	        //カード情報を取得
	        return card_info;
	    }
}
