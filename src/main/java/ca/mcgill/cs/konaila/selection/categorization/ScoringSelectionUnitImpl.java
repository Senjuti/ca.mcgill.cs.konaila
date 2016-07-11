package ca.mcgill.cs.konaila.selection.categorization;

public abstract class ScoringSelectionUnitImpl implements ScoringSelectionUnit {	

	protected int score;
	 protected int cid;
	 protected int uid;
	 
	 public ScoringSelectionUnitImpl() {}
	 
	 public ScoringSelectionUnitImpl(int cid, int uid) {
		 this.cid = cid;
		 this.uid = uid;
	 }
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	@Override
	public boolean equals(Object obj) {
		ScoringSelectionUnitImpl o = (ScoringSelectionUnitImpl)obj;
		return cid==o.cid && uid==o.uid;
	}

}
