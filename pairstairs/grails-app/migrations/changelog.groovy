databaseChangeLog = {

	changeSet(author: "ben", id: "1367347255359-2") {
		createTable(tableName: "pairing") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "pairingPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date", type: "timestamp") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "ben (generated)", id: "1367347255359-3") {
		createTable(tableName: "pairing_coder") {
			column(name: "pairing_coders_id", type: "bigint")

			column(name: "coder_id", type: "bigint")
		}
	}

	changeSet(author: "ben (generated)", id: "1367347255359-4") {
		createTable(tableName: "stairs") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "stairsPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "ben (generated)", id: "1367347255359-5") {
		createTable(tableName: "stairs_pairing") {
			column(name: "stairs_pairings_id", type: "bigint")

			column(name: "pairing_id", type: "bigint")
		}
	}

	changeSet(author: "ben (generated)", id: "1367347255359-6") {
		createTable(tableName: "team") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "teamPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "stairs_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "ben (generated)", id: "1367347255359-7") {
		createTable(tableName: "team_coder") {
			column(name: "team_coders_id", type: "bigint")

			column(name: "coder_id", type: "bigint")
		}
	}

	changeSet(author: "ben (generated)", id: "1367347255359-15") {
		createIndex(indexName: "name_uniq_1367347255295", tableName: "coder", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "ben (generated)", id: "1367347255359-16") {
		createIndex(indexName: "name_uniq_1367347255316", tableName: "team", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "ben (generated)", id: "1367347255359-8") {
		addForeignKeyConstraint(baseColumnNames: "coder_id", baseTableName: "pairing_coder", constraintName: "FK815C4BEE85AB1BBF", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "coder", referencesUniqueColumn: "false")
	}

	changeSet(author: "ben (generated)", id: "1367347255359-9") {
		addForeignKeyConstraint(baseColumnNames: "pairing_coders_id", baseTableName: "pairing_coder", constraintName: "FK815C4BEE428CF02", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "pairing", referencesUniqueColumn: "false")
	}

	changeSet(author: "ben (generated)", id: "1367347255359-10") {
		addForeignKeyConstraint(baseColumnNames: "pairing_id", baseTableName: "stairs_pairing", constraintName: "FK465C71D353F6FF9F", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "pairing", referencesUniqueColumn: "false")
	}

	changeSet(author: "ben (generated)", id: "1367347255359-11") {
		addForeignKeyConstraint(baseColumnNames: "stairs_pairings_id", baseTableName: "stairs_pairing", constraintName: "FK465C71D38A04DB7F", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "stairs", referencesUniqueColumn: "false")
	}

	changeSet(author: "ben (generated)", id: "1367347255359-12") {
		addForeignKeyConstraint(baseColumnNames: "stairs_id", baseTableName: "team", constraintName: "FK36425DFF2181F5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "stairs", referencesUniqueColumn: "false")
	}

	changeSet(author: "ben (generated)", id: "1367347255359-13") {
		addForeignKeyConstraint(baseColumnNames: "coder_id", baseTableName: "team_coder", constraintName: "FKBA63BBC385AB1BBF", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "coder", referencesUniqueColumn: "false")
	}

	changeSet(author: "ben (generated)", id: "1367347255359-14") {
		addForeignKeyConstraint(baseColumnNames: "team_coders_id", baseTableName: "team_coder", constraintName: "FKBA63BBC3B9652C42", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "team", referencesUniqueColumn: "false")
	}

	include file: 'add-date-to-pairing.groovy'
}
