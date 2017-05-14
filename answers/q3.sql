#Intial stored prodeure to iterate through sometable2 and invoke thee
#split_name procedure
DELIMITER $$
CREATE DEFINER=`root`@`%` PROCEDURE `split_name`(i INT)
BEGIN
  DECLARE str_id INT;
  DECLARE str_name VARCHAR(50);
  DECLARE str_name_split VARCHAR(50);
  DECLARE name_size INT;

  SELECT id, name
  INTO str_id, str_name
  FROM sometbl WHERE id=i LIMIT 1;

  SET name_size = LENGTH(str_name) - LENGTH(REPLACE(str_name, '|', '')) + 1;

  WHILE name_size > 0 DO
    SET str_name_split = SUBSTRING_INDEX(str_name, '|', -1);
    SET str_name = SUBSTRING_INDEX(str_name, '|', name_size - 1);
    INSERT INTO sometbl2 (id, name) VALUES (i, str_name_split);
    SET name_size = name_size - 1;
  END WHILE;
END$$
DELIMITER ;

#Stored procedure to split name
DELIMITER $$
CREATE DEFINER=`root`@`%` PROCEDURE `parse`()
BEGIN
  DECLARE done BOOLEAN DEFAULT 0;
  DECLARE i INT;
  DECLARE cursor_id CURSOR FOR SELECT id FROM sometbl WHERE name LIKE '%|%';
  DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done=1;
  OPEN cursor_id;
  FETCH cursor_id INTO i;
  REPEAT
    CALL split_name(i);
    FETCH cursor_id INTO i;
  UNTIL done END REPEAT;
  CLOSE cursor_id;

END$$
DELIMITER ;


