Neo4j Async Batch Writer Performance Test
=========================================

Run:

	ruby generate.rb
	
This will create a requests.csv file, you'll want to copy that into:
	
	src/test/resources/data/requests.csv
	
Open the project, run the "Engine" inside Test/Scala.

Choose 0 for async, 1 for sync.