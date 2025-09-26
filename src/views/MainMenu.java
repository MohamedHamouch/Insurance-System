package views;

import helpers.InputHelper;

public class MainMenu {
    private final AdvisorView advisorView = new AdvisorView();
    private final ClientView clientView = new ClientView();
    private final ContractView contractView = new ContractView();
    private final ClaimView claimView = new ClaimView(); // Add this line

    public static void run() {
        MainMenu app = new MainMenu();
        app.show();
    }

    public void show() {
        while (true) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Manage Advisors");
            System.out.println("2. Manage Clients");
            System.out.println("3. Manage Contracts");
            System.out.println("4. Manage Claims"); // Add this line
            System.out.println("0. Exit");
            int choice = InputHelper.getUserChoice("Choose an option: ", 0, 4);

            switch (choice) {
                case 1:
                    showAdvisorMenu();
                    break;
                case 2:
                    showClientMenu();
                    break;
                case 3:
                    showContractMenu();
                    break;
                case 4:
                    showClaimMenu();
                    break; // Add this line
                case 0:
                    System.out.println("Goodbye!");
                    return;
            }
        }
    }

    private void showAdvisorMenu() {
        while (true) {
            System.out.println("\n--- MANAGE ADVISORS ---");
            System.out.println("1. Add Advisor");
            System.out.println("2. Delete Advisor by ID");
            System.out.println("3. Search Advisor by ID");
            System.out.println("4. Show Clients of an Advisor by ID");
            System.out.println("0. Back");
            int choice = InputHelper.getUserChoice("Choose an option: ", 0, 4);
            switch (choice) {
                case 1:
                    advisorView.addAdvisor();
                    break;
                case 2:
                    advisorView.deleteAdvisorById();
                    break;
                case 3:
                    advisorView.searchAdvisorById();
                    break;
                case 4:
                    advisorView.showClientsOfAdvisor();
                    break;
                case 0:
                    return;
            }
        }
    }

    private void showClientMenu() {
        while (true) {
            System.out.println("\n--- MANAGE CLIENTS ---");
            System.out.println("1. Add Client");
            System.out.println("2. Delete Client by ID");
            System.out.println("3. Search Client by Last Name and Sort");
            System.out.println("4. Search Client by ID");
            System.out.println("5. Show Clients of an Advisor by Advisor ID");
            System.out.println("0. Back");
            int choice = InputHelper.getUserChoice("Choose an option: ", 0, 5);
            switch (choice) {
                case 1:
                    clientView.addClient();
                    break;
                case 2:
                    clientView.deleteClientById();
                    break;
                case 3:
                    clientView.searchClientByLastNameAndSort();
                    break;
                case 4:
                    clientView.searchClientById();
                    break;
                case 5:
                    clientView.showClientsOfAdvisor();
                    break;
                case 0:
                    return;
            }
        }
    }

    private void showContractMenu() {
        while (true) {
            System.out.println("\n--- MANAGE CONTRACTS ---");
            System.out.println("1. Add Contract");
            System.out.println("2. Show Contract Info by ID");
            System.out.println("3. Delete Contract by ID");
            System.out.println("4. Show Contracts of a Client by Client ID");
            System.out.println("0. Back");
            int choice = InputHelper.getUserChoice("Choose an option: ", 0, 4);
            switch (choice) {
                case 1:
                    contractView.addContract();
                    break;
                case 2:
                    contractView.showContractInfoById();
                    break;
                case 3:
                    contractView.deleteContractById();
                    break;
                case 4:
                    contractView.showContractsOfClient();
                    break;
                case 0:
                    return;
            }
        }
    }

    private void showClaimMenu() {
        while (true) {
            System.out.println("\n--- MANAGE CLAIMS ---");
            System.out.println("1. Add Claim");
            System.out.println("2. Delete Claim by ID");
            System.out.println("3. Calculate Total Cost of Claims for a Client by Client ID");
            System.out.println("4. Search Claim by ID");
            System.out.println("5. Show Claims of a Contract by Contract ID");
            System.out.println("6. Show Claims Sorted by Cost Descending");
            System.out.println("7. Show Claims by Client ID");
            System.out.println("8. Show Claims Before a Given Date");
            System.out.println("9. Show Claims with Cost Greater Than a Given Amount");
            System.out.println("0. Back");
            int choice = InputHelper.getUserChoice("Choose an option: ", 0, 9);

            switch (choice) {
                case 1:
                    claimView.addClaim();
                    break;
                case 2:
                    claimView.deleteClaimById();
                    break;
                case 3:
                    claimView.calculateTotalCostForClient();
                    break;
                case 4:
                    claimView.searchClaimById();
                    break;
                case 5:
                    claimView.showClaimsOfContract();
                    break;
                case 6:
                    claimView.showClaimsSortedByCostDesc();
                    break;
                case 7:
                    claimView.showClaimsByClientId();
                    break;
                case 8:
                    claimView.showClaimsBeforeDate();
                    break;
                case 9:
                    claimView.showClaimsWithCostGreaterThan();
                    break;
                case 0:
                    return;
            }
        }
    }
}