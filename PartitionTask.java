package com.partition.demo;

//#Given a set S of positive integers, determine if it can be partitioned into three disjoint subsets that 
//# all have the same sum, and they cover S.
//# S = { 7, 3, 2, 1, 5, 4, 8 }
public class PartitionTask {
	 
	public static void main(String[] args) {
			int inputArrary[] = {7, 3, 2, 1, 5, 4, 8 }; //took an example and it can be passed as dynamically
		    int N = inputArrary.length;
		    int subsetCount = 3; //took an example and it can be passed as dynamically
		    boolean partionFlag = partitionCheck(inputArrary, N, subsetCount);
		    if (partionFlag)
		        System.out.println("Given array with"+subsetCount+" Partitions is possible.");
		    else
		        System.out.println("Given array with"+subsetCount+" Partitoons is not possible.");
	}

	static boolean partitionCheck(int arr[], int N, int K) {
		boolean recursionFlag=false;
		if (K == 1)
			return true;

		if (N < K)
			return false;

		int sum = 0;
		for (int i = 0; i < N; i++)
			sum += arr[i];
		if (sum % K != 0)
			return false;

		int subset = sum / K;
		int[] subsetSum = new int[K];
		boolean[] taken = new boolean[N];

		for (int i = 0; i < K; i++)
			subsetSum[i] = 0;

		for (int i = 0; i < N; i++)
			taken[i] = false;

		subsetSum[0] = arr[N - 1];
		taken[N - 1] = true;
		
		recursionFlag=partitionRecursiveCheck(arr, subsetSum, taken, subset, K, N, 0, N - 1);
		
		return recursionFlag;
	}

	static boolean partitionRecursiveCheck(int arr[], int subsetSum[], boolean taken[], int subset, int K, int N,
			int currentIndex, int limitIndex) {
		if (subsetSum[currentIndex] == subset) {
			if (currentIndex == K - 2)
				return true;

			return partitionRecursiveCheck(arr, subsetSum, taken, subset, K, N, currentIndex + 1, N - 1);
		}
		for (int i = limitIndex; i >= 0; i--) {
			if (taken[i])
				continue;
			int tmp = subsetSum[currentIndex] + arr[i];

			if (tmp <= subset) {
				taken[i] = true;
				subsetSum[currentIndex] += arr[i];
				boolean nxt = partitionRecursiveCheck(arr, subsetSum, taken, subset, K, N, currentIndex, i - 1);
				taken[i] = false;
				subsetSum[currentIndex] -= arr[i];
				if (nxt)
					return true;
			}
		}
		return false;
	}

}
