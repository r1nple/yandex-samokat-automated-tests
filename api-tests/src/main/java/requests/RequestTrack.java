package requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Номер трека в теле запроса при отмене заказа
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestTrack {

    private int track;

}
