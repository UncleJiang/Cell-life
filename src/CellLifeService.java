import java.util.Random;


public class CellLifeService {
	
	//方向数组
	private int[] dirt = {-1, 0, 1};
	
	/**
	 * 给定坐标点，计算其邻居存活数量
	 * @param now 当前细胞数组
	 * @param x   横坐标
	 * @param y   纵坐标
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
	 * 生成下一代细胞数组
	 * @param now 细胞数组
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
				if(now.getCell(i, j) == CellState.LIVE.getValue()&&(liveCount < 2 || liveCount > 3)){  //邻居过少或过多，死亡
					next.setCell(i, j, CellState.DEAD.getValue());
				}else if(now.getCell(i, j) == CellState.LIVE.getValue()&& (2 <= liveCount && liveCount <= 3)) { //正常，生存
                    next.setCell(i, j, CellState.LIVE.getValue());
                } else if (CellState.DEAD.getValue() == now.getCell(i, j) && (3 == liveCount)) { //复活
                    next.setCell(i, j, CellState.LIVE.getValue());
                }
				
			}
		}
		return next;
	}
	
	/**
	 * 初始化，随机生成一代细胞数组
	 * @return firstArray
	 */
	public CellularArray randInit(){
		int defaultRow,defaultCol;
		defaultRow = 8;
		defaultCol = 8;
		//未实现自行设置行列数来初始化的功能
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
