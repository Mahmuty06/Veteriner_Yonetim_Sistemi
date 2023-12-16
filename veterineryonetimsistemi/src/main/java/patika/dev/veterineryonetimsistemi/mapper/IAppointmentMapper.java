package patika.dev.veterineryonetimsistemi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import patika.dev.veterineryonetimsistemi.dto.request.AppointmentRequest;
import patika.dev.veterineryonetimsistemi.dto.response.AppointmentResponse;
import patika.dev.veterineryonetimsistemi.entity.Appointment;

import java.util.List;

@Mapper
public interface IAppointmentMapper {

    Appointment asEntity(AppointmentRequest appointmentRequest);

    AppointmentResponse asOutPut(Appointment appointment);

    List<AppointmentResponse> asOutPut(List<Appointment> appointmentList);

    void update(@MappingTarget Appointment entity, AppointmentRequest request);
}
