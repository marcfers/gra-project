package br.com.texo.gra;

final class AwardsInterval implements Comparable<AwardsInterval> {

	private final int previousWin;
	private final int followingWin;

	public AwardsInterval(final int previousWin, final int followingWin) {
		this.previousWin = previousWin;
		this.followingWin = followingWin;
	}

	public int getPreviousWin() {
		return this.previousWin;
	}

	public int getFollowingWin() {
		return this.followingWin;
	}
	
	public int getInterval() {
		return this.followingWin - this.previousWin;
	}

	@Override
	public int compareTo(final AwardsInterval o) {
		return Integer.valueOf(this.getInterval()).compareTo(Integer.valueOf(o.getInterval()));
	}

}
