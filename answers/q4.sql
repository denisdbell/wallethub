#Query to select the number of days issue wass opened by date range
SELECT open_date, COUNT(open_date) FROM BUGS WHERE open_date BETWEEN date("2012-09-05") AND date("2012-09-05")  GROUP BY open_date;
