Functional Requirements:

- Entry Flow:
1. Vehicle arrives at entry gate 
2. Generate ticket and assign a parking spot to the vehicle
3. Mark the parking spot as occupied
4. Return EntryResult as success or failure status

- Exit Flow:
1. User present Ticket at ticket counter to exit
2. Calculate fee based on the pricing rules (minimum of flat or hourly pricing)
3. Process the paymemnt through gateway
4. Mark the parking spot as available
5. Return ExitResult as success or failure status

- Admin Configuration
1. Add/Edit/Delete parking slots and floors
2. Configure pricing rules (flat or hourly)
3. Update flat and hourly timings for vehicle types
4. View current parking status

