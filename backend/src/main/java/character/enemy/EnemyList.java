package character.enemy;

public class EnemyList {
	Enemy[] enemyList;
	public EnemyList() {
		this.enemyList = new Enemy[] {
			new Slime(),
			new Goblin()
		};
	}
	
	public Enemy[] useEnemyList() {
		return this.enemyList;
	}
	
	public Enemy getEnemy(int index) {
		return this.enemyList[index];
	}
	
	public Enemy getRandomEnemy() {
		int index = (int)(Math.random() * this.enemyList.length);
		return this.enemyList[index];
	}
}
