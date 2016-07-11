SELECT DISTINCT cid, *
FROM results
WHERE (user NOT LIKE 'P02%' AND user NOT LIKE 'P03%') 
ORDER BY random()

SELECT R.user, R.taskNumber, R.knapsack, R.greedy, R.baseline, Y.user, R.taskNumber, Y.knapsack, Y.greedy, Y.baseline
FROM results R, results Y
WHERE R.cid=Y.cid AND R.size=Y.size
AND Y.user LIKE 'P18%'
AND R.user NOT LIKE 'P18%' AND R.user NOT LIKE 'P02%' AND R.user NOT LIKE 'P03%' AND R.user NOT LIKE 'P17%'

/* knapsack better than baseline: 9 out of 37 disagrees */
SELECT R.cid, R.size, R.user, R.taskNumber, R.knapsack-R.baseline>0 , Y.knapsack-Y.baseline>0, (R.knapsack-R.baseline>0 ) + (Y.knapsack-Y.baseline>0)
FROM results R, results Y
WHERE R.cid=Y.cid AND R.size=Y.size
AND Y.user LIKE 'P18%'
AND R.user NOT LIKE 'P18%' AND R.user NOT LIKE 'P02%' AND R.user NOT LIKE 'P03%' AND R.user NOT LIKE 'P17%'
AND R.summaryKnapsack NOT LIKE R.summaryBaseline
	/* tabulated */
	SELECT R.user, R.taskNumber, R.knapsack>R.baseline , R.knapsack=R.baseline, R.knapsack<R.baseline, Y.knapsack>Y.baseline, Y.knapsack=Y.baseline, Y.knapsack<Y.baseline
	FROM results R, results Y
	WHERE R.cid=Y.cid AND R.size=Y.size
	AND Y.user LIKE 'P18%'
	AND R.user NOT LIKE 'P18%' AND R.user NOT LIKE 'P02%' AND R.user NOT LIKE 'P03%' AND R.user NOT LIKE 'P17%'
	AND R.summaryKnapsack NOT LIKE R.summaryBaseline
	AND R.knapsack<R.baseline AND Y.knapsack<Y.baseline

/* greedy better than baseline: 5 disagrees out of 28 */
SELECT R.user, R.taskNumber, R.greedy-R.baseline>0 , Y.greedy-Y.baseline>=0, (R.greedy-R.baseline>=0 ) + (Y.greedy-Y.baseline>=0)
FROM results R, results Y
WHERE R.cid=Y.cid AND R.size=Y.size
AND Y.user LIKE 'P18%'
AND R.user NOT LIKE 'P18%' AND R.user NOT LIKE 'P02%' AND R.user NOT LIKE 'P03%' AND R.user NOT LIKE 'P17%'
AND R.summaryGreedy NOT LIKE R.summaryBaseline
	/* tabulated 3x3*/
	SELECT R.user, R.taskNumber, R.greedy>R.baseline , R.greedy=R.baseline, R.greedy<R.baseline, Y.greedy>Y.baseline, Y.greedy=Y.baseline, Y.greedy<Y.baseline
	FROM results R, results Y
	WHERE R.cid=Y.cid AND R.size=Y.size
	AND Y.user LIKE 'P18%'
	AND R.user NOT LIKE 'P18%' AND R.user NOT LIKE 'P02%' AND R.user NOT LIKE 'P03%' AND R.user NOT LIKE 'P17%'
	AND R.summaryGreedy NOT LIKE R.summaryBaseline
	AND R.greedy<R.baseline AND Y.greedy<Y.baseline
	

/* Knapsack agreement */	
SELECT R.user, R.taskNumber, R.knapsack>R.baseline , R.knapsack=R.baseline, R.knapsack<R.baseline, Y.knapsack>Y.baseline, Y.knapsack=Y.baseline, Y.knapsack<Y.baseline
FROM results R, results Y
WHERE R.cid=Y.cid AND R.size=Y.size
AND Y.user LIKE 'P17%'
AND R.user LIKE 'P05%'	

	
/* Greedy agreement */	
SELECT R.user, R.taskNumber, R.greedy>R.baseline , R.greedy=R.baseline, R.greedy<R.baseline, Y.greedy>Y.baseline, Y.greedy=Y.baseline, Y.greedy<Y.baseline
FROM results R, results Y
WHERE R.cid=Y.cid AND R.size=Y.size
AND Y.user LIKE 'P17%'
AND R.user LIKE 'P05%'


