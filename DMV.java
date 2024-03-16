package Assignment3;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class DMV {
    private String state;
    private List<Registration> registrations;
    private List<Citation> citations;

    public DMV(String state) {
        this.state = state;
        this.registrations = new ArrayList<>();
        this.citations = new ArrayList<>();
    }

    public void registerVehicle(Owner owner, Vehicle vehicle) throws Exception {
        if (hasPendingCitations(vehicle)) {
            throw new Exception("Cannot register vehicle. Pending citations exist.");
        }

        if (hasActiveRegistration(vehicle)) {
            throw new Exception("Cannot register vehicle. Active registration already exists.");
        }

        Registration registration = new Registration(owner, vehicle);
        registrations.add(registration);
    }

    public void registerCitation(Citation citation) {
        citations.add(citation);
    }

    public void listRegistrations() {
        for (Registration registration : registrations) {
            System.out.println("Registration ID: " + registration.getUniqueID() +
                    ", Plate: " + registration.getPlate() +
                    ", Owner: " + registration.getOwners()[0].getFirstName() + " " + registration.getOwners()[0].getLastName());
        }
    }

    public void listCitations() {
        for (Citation citation : citations) {
            System.out.println("Citation ID: " + citation.getDate() +
                    ", Offence Code: " + citation.getOffenceCode() +
                    ", Amount: " + citation.getAmount() +
                    ", Status: " + citation.getStatus());
        }
    }

    public Registration searchRegistrationByPlate(String plate) {
        for (Registration registration : registrations) {
            if (registration.getPlate().equals(plate)) {
                return registration;
            }
        }
        return null;
    }

    public Registration searchRegistrationByID(String id) {
        for (Registration registration : registrations) {
            if (String.valueOf(registration.getUniqueID()).equals(id)) {
                return registration;
            }
        }
        return null;
    }

    public List<Registration> searchRegistrationByOwner(Owner owner) {
        List<Registration> ownerRegistrations = new ArrayList<>();
        for (Registration registration : registrations) {
            for (Owner regOwner : registration.getOwners()) {
                if (regOwner.equals(owner)) {
                    ownerRegistrations.add(registration);
                    break;
                }
            }
        }
        return ownerRegistrations;
    }

    public List<Citation> searchCitationByRegistration(Registration registration) {
        List<Citation> registrationCitations = new ArrayList<>();
        for (Citation citation : citations) {
            if (citation.getRegistration().equals(registration)) {
                registrationCitations.add(citation);
            }
        }
        return registrationCitations;
    }

    public List<Citation> searchCitationByOwner(Owner owner) {
        List<Citation> ownerCitations = new ArrayList<>();
        for (Citation citation : citations) {
            if (citation.getRegistration().getOwners()[0].equals(owner)) {
                ownerCitations.add(citation);
            }
        }
        return ownerCitations;
    }

    public Citation searchCitationById(String id) {
        for (Citation citation : citations) {
            if (String.valueOf(citation.getDate()).equals(id)) {
                return citation;
            }
        }
        return null;
    }

    private boolean hasPendingCitations(Vehicle vehicle) {
        for (Citation citation : citations) {
            if (citation.getRegistration().getVehicle().equals(vehicle) && !citation.getStatus().equals("Paid")) {
                return true;
            }
        }
        return false;
    }

    private boolean hasActiveRegistration(Vehicle vehicle) {
        for (Registration registration : registrations) {
            if (registration.getVehicle().equals(vehicle) && registration.getEndDate().after(new Date())) {
                return true;
            }
        }
        return false;
    }
}