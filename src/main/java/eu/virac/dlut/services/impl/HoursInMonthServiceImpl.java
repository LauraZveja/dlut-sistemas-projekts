package eu.virac.dlut.services.impl;

import java.util.ArrayList;

import eu.virac.dlut.models.Employee;
import eu.virac.dlut.models.helpers.EmployeeDTO;
import eu.virac.dlut.models.helpers.HoursInMonthDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.virac.dlut.models.HoursInMonth;
import eu.virac.dlut.repos.IHoursInMonthRepo;
import eu.virac.dlut.services.IHoursInMonthService;

@Service
public class HoursInMonthServiceImpl implements IHoursInMonthService {

    @Autowired
    IHoursInMonthRepo hoursInMonthRepo;

    @Override
    public boolean insertHoursInMonth(int year, int month, double hours) {

        HoursInMonth h = hoursInMonthRepo.findByYearAndMonth(year, month);
        if (h == null) {
            HoursInMonth hNew = new HoursInMonth(year, month, hours);
            hoursInMonthRepo.save(hNew);
            return true;
        } else {
            h.setHoursInMonth(hours);
            hoursInMonthRepo.save(h);
            return true;
        }


    }

    @Override
    public HoursInMonth updateHoursInMonthById(int id, int year, int month, double hours) throws Exception {
        if (hoursInMonthRepo.existsById(id)) {
            HoursInMonth hEdit = hoursInMonthRepo.findById(id).get();
            hEdit.setYear(year);
            hEdit.setMonth(month);
            hEdit.setHoursInMonth(hours);
            hoursInMonthRepo.save(hEdit);
            return hEdit;
        } else
            throw new Exception("ID not found!");
    }

    @Override
    public ArrayList<HoursInMonth> selectAllHoursInMonths() {
        return (ArrayList<HoursInMonth>) hoursInMonthRepo.findAll();
    }

    @Override
    public HoursInMonth selectHoursInMonthByYearAndMonth(int year, int month) throws Exception {
        HoursInMonth h = hoursInMonthRepo.findByYearAndMonth(year, month);
        if (h != null)
            return h;

        throw new Exception("Hours in month and year not found!");
    }

    @Override
    public HoursInMonthDTO insertHoursInMonthDTO(HoursInMonthDTO hoursInMonthDTO) throws Exception {
        int year = hoursInMonthDTO.getYear();
        int month = hoursInMonthDTO.getMonth();

        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Month must be between 1 and 12. 1 for January, 12 for December.");
        }
        if (year < 1994 || year > 2124) {
            throw new IllegalArgumentException("Year must be between 1994 and 2124.");
        }


        HoursInMonth existingHoursInMonth = hoursInMonthRepo.findByYearAndMonth(hoursInMonthDTO.getYear(), hoursInMonthDTO.getMonth());
        if (existingHoursInMonth != null) {
            throw new Exception("Hours for this month and year have already been submitted.");
        }
        HoursInMonth hoursInMonth = new HoursInMonth(hoursInMonthDTO.getYear(), hoursInMonthDTO.getMonth(), hoursInMonthDTO.getHoursInMonth());
        hoursInMonthRepo.save(hoursInMonth);
        hoursInMonthDTO.setIdHoursInMonth(hoursInMonth.getIdHoursInMonth());
        return hoursInMonthDTO;
    }

    @Override
    public ArrayList<HoursInMonthDTO> retrieveAllHoursInMonthDTO() {
        ArrayList<HoursInMonthDTO> result = new ArrayList<>();
        ArrayList<HoursInMonth> allHoursInMonth = (ArrayList<HoursInMonth>) hoursInMonthRepo.findAll();
        for (HoursInMonth hoursInMonth : allHoursInMonth) {
            result.add(new HoursInMonthDTO(hoursInMonth.getIdHoursInMonth(), hoursInMonth.getYear(), hoursInMonth.getMonth(), hoursInMonth.getHoursInMonth()));
        }
        return result;
    }

    @Override
    public HoursInMonthDTO updateHoursInMonthDTO(HoursInMonthDTO hoursInMonthDTO) throws Exception {
        HoursInMonth hoursInMonth = hoursInMonthRepo.findById(hoursInMonthDTO.getIdHoursInMonth())
                .orElseThrow(() -> new EntityNotFoundException("Hours in month not found."));
        hoursInMonth.setHoursInMonth(hoursInMonthDTO.getHoursInMonth());
        hoursInMonthRepo.save(hoursInMonth);
        return new HoursInMonthDTO(hoursInMonth.getIdHoursInMonth(), hoursInMonth.getYear(), hoursInMonth.getMonth(), hoursInMonth.getHoursInMonth());
    }

    @Override
    public void deleteHoursInMonthDTO(HoursInMonthDTO hoursInMonthDTO) {
        HoursInMonth hoursInMonth = hoursInMonthRepo.findById(hoursInMonthDTO.getIdHoursInMonth())
                .orElseThrow(() -> new EntityNotFoundException("Hours in month not found."));
        hoursInMonthRepo.delete(hoursInMonth);
    }

    @Override
    public HoursInMonthDTO selectHoursInMonthByYearAndMonthDTO(int year, int month) throws Exception {
        HoursInMonth existingHoursInMonth = hoursInMonthRepo.findByYearAndMonth(year, month);
        if (existingHoursInMonth != null) {
            return new HoursInMonthDTO(existingHoursInMonth.getIdHoursInMonth(), existingHoursInMonth.getYear(), existingHoursInMonth.getMonth(), existingHoursInMonth.getHoursInMonth());
        } else {
            throw new Exception("Hours in month and year not found!");
        }
    }

    @Override
    public ArrayList<HoursInMonthDTO> selectAllHoursInYear(int year) {
        ArrayList<HoursInMonthDTO> result = new ArrayList<>();
        for (int month = 1; month <= 12; month++) {
            HoursInMonth existingHoursInMonth = hoursInMonthRepo.findByYearAndMonth(year, month);
            if (existingHoursInMonth != null) {
                result.add(new HoursInMonthDTO(existingHoursInMonth.getIdHoursInMonth(), existingHoursInMonth.getYear(), existingHoursInMonth.getMonth(), existingHoursInMonth.getHoursInMonth()));
            }
        }
        return result;
    }

    @Override
    public ArrayList<HoursInMonthDTO> insertHoursInYear(ArrayList<HoursInMonthDTO> hoursInYearDTO) throws Exception {
        ArrayList<HoursInMonthDTO> insertedHours = new ArrayList<>();

        for (HoursInMonthDTO hoursInMonthDTO : hoursInYearDTO) {
            int year = hoursInMonthDTO.getYear();
            int month = hoursInMonthDTO.getMonth();

            if (month < 1 || month > 12) {
                throw new IllegalArgumentException("Month must be between 1 and 12. 1 for January, 12 for December.");
            }
            if (year < 1994 || year > 2124) {
                throw new IllegalArgumentException("Year must be between 1994 and 2124.");
            }

            HoursInMonth existingHoursInMonth = hoursInMonthRepo.findByYearAndMonth(year, month);
            if (existingHoursInMonth != null) {
                throw new Exception("Hours for this month and year have already been submitted.");
            }

            HoursInMonth hoursInMonth = new HoursInMonth(year, month, hoursInMonthDTO.getHoursInMonth());
            hoursInMonthRepo.save(hoursInMonth);
            hoursInMonthDTO.setIdHoursInMonth(hoursInMonth.getIdHoursInMonth());
            insertedHours.add(hoursInMonthDTO);
        }

        return insertedHours;
    }

}
