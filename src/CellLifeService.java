import java.util.Random;


public class CellLifeService {
	
	//��������
	private int[] dirt = {-1, 0, 1};
	
	/**
	 * ��������㣬�������ھӴ������
	 * @param now ��ǰϸ������
	 * @param x   ������
	 * @param y   ������
	 */
	private int countLiveNeighbor(CellularArray now, int x, int y){
		int count = 0;
		for(int i = 0; i < 3; i++){
			for(int j = 0; j< 3; j++){
				if(CellState.LIVE.getValue() == now.getCell(x + this.dirt[i], y + this.dirt[j])){
					count++;
				}
			}
		}
		if(CellState.LIVE.getValue() == now.getCell(x, y)){
			count--;
		}
		return count;
	}
	
	/**
	 * ������һ��ϸ������
	 * @param now ϸ������
	 */
	public CellularArray generate(CellularArray now){
		if(now == null){
			return null;
		}
		int liveCount;
		CellularArray next = new CellularArray(now.getRow(),now.getCol());
		
		for(int i = 1; i < next.getRow(); i++){
			for(int j = 1; j<next.getCol(); j++){
				liveCount = this.countLiveNeighbor(now, i, j);
				if(now.getCell(i, j) == CellState.LIVE.getValue()&&(liveCount < 2 || liveCount > 3)){  //�ھӹ��ٻ���࣬����
					next.setCell(i, j, CellState.DEAD.getValue());
				}else if(now.getCell(i, j) == CellState.LIVE.getValue()&& (2 <= liveCount && liveCount <= 3)) { //����������
                    next.setCell(i, j, CellState.LIVE.getValue());
                } else if (CellState.DEAD.getValue() == now.getCell(i, j) && (3 == liveCount)) { //����
                    next.setCell(i, j, CellState.LIVE.getValue());
                }
				
			}
		}
		return next;
	}
	
	/**
	 * ��ʼ�����������һ��ϸ������
	 * @return firstArray
	 */
	public CellularArray randInit(){
		int defaultRow,defaultCol;
		defaultRow = 8;
		defaultCol = 8;
		//δʵ��������������������ʼ���Ĺ���
		CellularArray firstArray = new CellularArray(defaultRow ,defaultCol);
		Random r = new Random();
		int value;
		for(int i = 1; i < firstArray.getRow() - 1; i++){
			for(int j = 1; j < firstArray.getCol() - 1; j++){
				value = r.nextInt(2);
				firstArray.setCell(i, j, value);
			}
		}
		return firstArray;
	}
}
