/* number of ratings per user */
SELECT user, count(*)
FROM results
WHERE (user NOT LIKE 'P02%' AND user NOT LIKE 'P03%')
GROUP BY user

/* knapsack - baseline not count the same summaries*/
SELECT user, sum(knapsack) - sum(baseline)
FROM results
WHERE (user NOT LIKE 'P02%' AND user NOT LIKE 'P03%') 
AND summaryKnapsack NOT LIKE summaryBaseline
GROUP BY user
/* knapsack - baseline all = 31 as of Harry (out of 324 entries)*/
SELECT sum(knapsack-baseline)
FROM results
WHERE (user NOT LIKE 'P02%' AND user NOT LIKE 'P03%' AND user NOT LIKE 'P18%') 
AND summaryKnapsack NOT LIKE summaryBaseline
/* AND size LIKE 'Big' /*27*/*/
/* AND size LIKE 'Small' /*3*/*/
/* AND numberOfNonEmptyLines >= 10 */

/* greedy - baseline not count the same summaries*/
SELECT user, sum(greedy) - sum(baseline)
FROM results
WHERE (user NOT LIKE 'P02%' AND user NOT LIKE 'P03%' AND user NOT LIKE 'P18%') 
AND summaryGreedy NOT LIKE summaryBaseline
GROUP BY user
/* greedy - baselinie all = 18 as of Harry out of 259 entries*/
SELECT sum(greedy-baseline)
FROM results
WHERE (user NOT LIKE 'P02%' AND user NOT LIKE 'P03%') 
AND summaryGreedy NOT LIKE summaryBaseline
/* AND size LIKE 'Big' /*26*/*/
/* AND size LIKE 'Small' /*-8*/*/



