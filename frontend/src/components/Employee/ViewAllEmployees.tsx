import React, { useEffect, useState } from 'react';
import { getEmployeesList } from '../../services/EmployeeService';
import IEmployeeData from '../../models/Employee';
import './ViewAllEmployees.css';

const ViewAllEmployees = () => {
    const [employees, setEmployees] = useState<IEmployeeData[]>([]);

    useEffect(() => {
        getEmployeesList()
            .then(data => {
                setEmployees(data);
            })
            .catch(error => console.error(error));
    }, []);

    return (
      <div className="container container-custom mt-3">
          <h2>Darbinieku saraksts</h2>
          <table className="table table-hover">
              <thead className="thead-dark">
                  <tr>
                      <th>ID</th>
                      <th>Vārds</th>
                      <th>Uzvārds</th>
                      <th>Amats</th>
                      <th>Ir ievēlēts</th>
                      <th>Darba līgums un datums</th>
                      <th>Nodaļa</th>
                  </tr>
              </thead>
              <tbody>
                  {employees.map(employee => (
                      <tr key={employee.idEmployee}>
                          <td>{employee.idEmployee}</td>
                          <td>{employee.name}</td>
                          <td>{employee.surname}</td>
                          <td>{employee.position}</td>
                          <td>{employee.elected ? 'Jā' : 'Nē'}</td>
                          <td>{employee.workContractNoDate}</td>
                          <td>{employee.departmentName}</td>
                          <td>
                              <button className="btn btn-outline-success me-2">Labot</button>
                              <button className="btn btn-outline-danger">Dzēst</button>
                          </td>
                      </tr>
                  ))}
              </tbody>
          </table>
          <button className="btn btn-outline-dark">Pievienot</button>
      </div>
  );
};

export default ViewAllEmployees;
