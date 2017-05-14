#Query to rank votes
SELECT *,   @curRank := @curRank + 1 AS rank FROM votes,
(SELECT @curRank := 0) r order by votes