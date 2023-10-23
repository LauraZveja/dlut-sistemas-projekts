package eu.virac.dlut.services;

import java.util.ArrayList;

import eu.virac.dlut.models.IndirectVUAS;


public interface IIndirectVUASService {

	ArrayList<IndirectVUAS> selectAllIndirectVuas();

	ArrayList<IndirectVUAS> selectIndirectVuasEmpolyee(int year, int month, int employeeId);

	IndirectVUAS selectOneIndirectVuasById(int indirectVuasId) throws Exception;
	
}
