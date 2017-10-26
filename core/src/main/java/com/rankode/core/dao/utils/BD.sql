-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema rankode
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema rankode
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `rankode` DEFAULT CHARACTER SET utf8 ;
USE `rankode` ;

-- -----------------------------------------------------
-- Table `rankode`.`DEVELOPERS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rankode`.`DEVELOPERS` (
  `LOGIN` VARCHAR(45) NOT NULL,
  `FIRST_NAME` VARCHAR(45) NULL,
  `LAST_NAME` VARCHAR(45) NULL,
  `PASSWORD` VARCHAR(45) NULL,
  `EMAIL` VARCHAR(45) NULL,
  `PROFILE_PICTURE` VARCHAR(45) NULL,
  PRIMARY KEY (`LOGIN`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rankode`.`PROJECTS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rankode`.`PROJECTS` (
  `ID` INT NOT NULL,
  `OWNER_LOGIN` VARCHAR(45) NOT NULL,
  `PROJECT_NAME` VARCHAR(45) NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_PROJECTS_DEVELOPERS1_idx` (`OWNER_LOGIN` ASC),
  CONSTRAINT `fk_PROJECTS_DEVELOPERS1`
    FOREIGN KEY (`OWNER_LOGIN`)
    REFERENCES `rankode`.`DEVELOPERS` (`LOGIN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rankode`.`COLLABORATORS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rankode`.`COLLABORATORS` (
  `DEVELOPERS_LOGIN` VARCHAR(45) NOT NULL,
  `PROJECTS_ID` INT NOT NULL,
  `XP` INT NULL,
  `ADMISSION_DATE` DATE NOT NULL,
  `DEMISSION_DATE` VARCHAR(45) NULL,
  INDEX `fk_COLLABORATORS_DEVELOPERS1_idx` (`DEVELOPERS_LOGIN` ASC),
  PRIMARY KEY (`DEVELOPERS_LOGIN`, `PROJECTS_ID`, `ADMISSION_DATE`),
  INDEX `fk_COLLABORATORS_PROJECTS1_idx` (`PROJECTS_ID` ASC),
  CONSTRAINT `fk_COLLABORATORS_DEVELOPERS1`
    FOREIGN KEY (`DEVELOPERS_LOGIN`)
    REFERENCES `rankode`.`DEVELOPERS` (`LOGIN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_COLLABORATORS_PROJECTS1`
    FOREIGN KEY (`PROJECTS_ID`)
    REFERENCES `rankode`.`PROJECTS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rankode`.`COMMITS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rankode`.`COMMITS` (
  `SHA` VARCHAR(45) NOT NULL,
  `COLLABORATORS_LOGIN` VARCHAR(45) NOT NULL,
  `DATA` DATETIME NULL,
  PRIMARY KEY (`SHA`),
  INDEX `fk_COMMITS_COLLABORATORS1_idx` (`COLLABORATORS_LOGIN` ASC),
  CONSTRAINT `fk_COMMITS_COLLABORATORS1`
    FOREIGN KEY (`COLLABORATORS_LOGIN`)
    REFERENCES `rankode`.`COLLABORATORS` (`DEVELOPERS_LOGIN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rankode`.`METRIC_GROUP`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rankode`.`METRIC_GROUP` (
  `ID` INT NOT NULL,
  `NAME` VARCHAR(45) NULL,
  `DESCRIPTION` VARCHAR(45) NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rankode`.`TARGETS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rankode`.`TARGETS` (
  `ID` INT NOT NULL,
  `NAME` VARCHAR(45) NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rankode`.`METRICS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rankode`.`METRICS` (
  `INITIALS` VARCHAR(5) NOT NULL,
  `GOUP_ID` INT NOT NULL,
  `TARGET_ID` INT NOT NULL,
  `DESCRIPTION` VARCHAR(45) NULL,
  PRIMARY KEY (`INITIALS`),
  INDEX `fk_METRICS_METRIC_TYPE1_idx` (`GOUP_ID` ASC),
  INDEX `fk_METRICS_TARGET1_idx` (`TARGET_ID` ASC),
  CONSTRAINT `fk_METRICS_METRIC_TYPE1`
    FOREIGN KEY (`GOUP_ID`)
    REFERENCES `rankode`.`METRIC_GROUP` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_METRICS_TARGET1`
    FOREIGN KEY (`TARGET_ID`)
    REFERENCES `rankode`.`TARGETS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rankode`.`METRICS_RESULTS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rankode`.`METRICS_RESULTS` (
  `COMMITS_SHA` VARCHAR(45) NOT NULL,
  `METRICS_INITIALS` VARCHAR(5) NOT NULL,
  `VALUE` DOUBLE NULL,
  `DELTA_VALUE` VARCHAR(45) NULL,
  `SOURCE` VARCHAR(45) NULL,
  PRIMARY KEY (`COMMITS_SHA`, `METRICS_INITIALS`),
  INDEX `fk_METRICS_RULES_has_COMMITS_COMMITS1_idx` (`COMMITS_SHA` ASC),
  INDEX `fk_METRICS_RESULTS_METRICS1_idx` (`METRICS_INITIALS` ASC),
  CONSTRAINT `fk_METRICS_RULES_has_COMMITS_COMMITS1`
    FOREIGN KEY (`COMMITS_SHA`)
    REFERENCES `rankode`.`COMMITS` (`SHA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_METRICS_RESULTS_METRICS1`
    FOREIGN KEY (`METRICS_INITIALS`)
    REFERENCES `rankode`.`METRICS` (`INITIALS`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rankode`.`HINTS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rankode`.`HINTS` (
  `ID` INT NOT NULL,
  `TEXT` VARCHAR(45) NULL,
  `INTERVALS_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_HINTS_INTERVALS1_idx` (`INTERVALS_ID` ASC),
  CONSTRAINT `fk_HINTS_INTERVALS1`
    FOREIGN KEY (`INTERVALS_ID`)
    REFERENCES `rankode`.`INTERVALS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rankode`.`REPOSITORY_ACCOUNTS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rankode`.`REPOSITORY_ACCOUNTS` (
  `LOGIN` INT NOT NULL,
  `NAME` VARCHAR(45) NULL,
  `DEVELOPERS_LOGIN` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`LOGIN`),
  INDEX `fk_REPOSITORY_ACCOUNT_DEVELOPERS1_idx` (`DEVELOPERS_LOGIN` ASC),
  CONSTRAINT `fk_REPOSITORY_ACCOUNT_DEVELOPERS1`
    FOREIGN KEY (`DEVELOPERS_LOGIN`)
    REFERENCES `rankode`.`DEVELOPERS` (`LOGIN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rankode`.`INTERVALS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rankode`.`INTERVALS` (
  `ID` INT NOT NULL,
  `MAX` INT NULL,
  `MIN` INT NULL,
  `DESCRIPTION` VARCHAR(45) NULL,
  `METRICS_INITIALS` VARCHAR(5) NOT NULL,
  `PROJECTS_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_INTERVALS_METRICS1_idx` (`METRICS_INITIALS` ASC),
  INDEX `fk_INTERVALS_PROJECTS1_idx` (`PROJECTS_ID` ASC),
  CONSTRAINT `fk_INTERVALS_METRICS1`
    FOREIGN KEY (`METRICS_INITIALS`)
    REFERENCES `rankode`.`METRICS` (`INITIALS`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_INTERVALS_PROJECTS1`
    FOREIGN KEY (`PROJECTS_ID`)
    REFERENCES `rankode`.`PROJECTS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rankode`.`INFLUENCES`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rankode`.`INFLUENCES` (
  `ID` INT NOT NULL,
  `NAME` VARCHAR(45) NULL,
  `DESCRIPTION` VARCHAR(45) NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rankode`.`XP_BASE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rankode`.`XP_BASE` (
  `LEVEL` INT NOT NULL,
  `TOTAL_XP` INT NULL,
  `NEXT_XP` INT NULL,
  PRIMARY KEY (`LEVEL`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rankode`.`NOTIFICATIONS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rankode`.`NOTIFICATIONS` (
  `ID` INT NOT NULL,
  `DEVELOPERS_LOGIN` VARCHAR(45) NOT NULL,
  `DESCRIPTION` VARCHAR(45) NULL,
  `TITLE` VARCHAR(45) NULL,
  `CONTENT` VARCHAR(45) NULL,
  `DATE` DATE NULL,
  `SEEN` TINYINT(1) NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_NOTIFICATION_DEVELOPERS1_idx` (`DEVELOPERS_LOGIN` ASC),
  CONSTRAINT `fk_NOTIFICATION_DEVELOPERS1`
    FOREIGN KEY (`DEVELOPERS_LOGIN`)
    REFERENCES `rankode`.`DEVELOPERS` (`LOGIN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rankode`.`INFLUENCES_has_METRICS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rankode`.`INFLUENCES_has_METRICS` (
  `INFLUENCES_ID` INT NOT NULL,
  `METRICS_INITIALS` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`INFLUENCES_ID`, `METRICS_INITIALS`),
  INDEX `fk_INFLUENCES_has_METRICS_METRICS1_idx` (`METRICS_INITIALS` ASC),
  INDEX `fk_INFLUENCES_has_METRICS_INFLUENCES1_idx` (`INFLUENCES_ID` ASC),
  CONSTRAINT `fk_INFLUENCES_has_METRICS_INFLUENCES1`
    FOREIGN KEY (`INFLUENCES_ID`)
    REFERENCES `rankode`.`INFLUENCES` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_INFLUENCES_has_METRICS_METRICS1`
    FOREIGN KEY (`METRICS_INITIALS`)
    REFERENCES `rankode`.`METRICS` (`INITIALS`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rankode`.`INVITES`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rankode`.`INVITES` (
  `NOTIFICATION_ID` INT NOT NULL,
  `RESPONSE` TINYINT(1) NULL,
  `PROJECTS_ID` INT NOT NULL,
  PRIMARY KEY (`NOTIFICATION_ID`),
  INDEX `fk_INVITE_NOTIFICATION1_idx` (`NOTIFICATION_ID` ASC),
  INDEX `fk_INVITE_PROJECTS1_idx` (`PROJECTS_ID` ASC),
  CONSTRAINT `fk_INVITE_NOTIFICATION1`
    FOREIGN KEY (`NOTIFICATION_ID`)
    REFERENCES `rankode`.`NOTIFICATIONS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_INVITE_PROJECTS1`
    FOREIGN KEY (`PROJECTS_ID`)
    REFERENCES `rankode`.`PROJECTS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rankode`.`HINTS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rankode`.`HINTS` (
  `ID` INT NOT NULL,
  `TEXT` VARCHAR(45) NULL,
  `INTERVALS_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_HINTS_INTERVALS1_idx` (`INTERVALS_ID` ASC),
  CONSTRAINT `fk_HINTS_INTERVALS1`
    FOREIGN KEY (`INTERVALS_ID`)
    REFERENCES `rankode`.`INTERVALS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
