package org.example.eticaretapp.dto.request;

import org.example.eticaretapp.entity.enums.computer.ComputerType;
import org.example.eticaretapp.entity.enums.computer.Harddisk;
import org.example.eticaretapp.entity.enums.computer.Ram;

import java.util.List;

public record FindComputerRequestDto(
		List<ComputerType> computerTypeList,
		List<Harddisk> harddiskList,
		List<Ram> ramList
) {
}